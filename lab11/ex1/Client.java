package ex1;

public class Client extends Person implements Observer {
    protected Client(String name) {
        super(name);
    }

    public void update(String message) {
        System.out.println(message);
    }

    public void bid(Product product, double price) {
        product.getManager().getNewBid(this, product, price);
    }
}
