package ex1;

public class Product {

    private static int idGenerator = 0;
    private final int id;
    private final String name;
    private final double price;
    private final Manager manager;
    private State state = State.STOCK;
    private double bestBid;
    private Client bestBidder;

    public Product(String name, double price, Manager manager) {
        this.id = idGenerator++;
        this.name = name;
        this.price = price;
        this.manager = manager;
    }

    public Client getBestBidder() {
        return this.bestBidder;
    }

    public Manager getManager() {
        return manager;
    }

    @Override
    public String toString() {
        return id + " (name: " + name + ", price: " + price + ")";
    }

    protected void startAuction(int maxDuration) {
        Product p = this;

        Thread t = new Thread(() -> {
            try {
                System.out.println(
                        "Auction for " + p + " started and is going to take " + maxDuration + " seconds");
                updateState(State.AUCTION);
                Thread.sleep(maxDuration * 1000L);
                finishAuction();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted");
                updateState(State.STOCK);
                Thread.currentThread().interrupt();
            }
        });
        t.start();
    }

    protected void updateState(State newState) {
        this.state = newState;
    }

    protected void finishAuction() {
        System.out.println("Finished Auction for " + this.name);
        notifySubscribersThatAuctionEnded();
        if (bestBidder != null) {
            updateState(State.SOLD);
            manager.sell(this);
            return;
        }
        updateState(State.STOCK);
    }

    protected void newBid(double price, Client person) {
        if (price > bestBid) {
            this.bestBid = price;
            this.bestBidder = person;
            notifySubscribersOfNewBids();
        }
    }

    protected void notifySubscribersOfNewBids() {
        String notification = "A new bid of " + bestBid + "has been placed for " + this;
        manager.notify(this, notification);
    }

    protected void notifySubscribersThatAuctionEnded() {
        String notification = "Auction ended for " + this.name + " to " + this.bestBidder.getName() + " by " + this.bestBid;
        manager.notify(this, notification);
    }
}
