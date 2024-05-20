package ex1;

import java.util.*;

public class Manager extends Person {
    private final Map<Product, Set<Client>> subscribers = new HashMap<>();

    protected Manager(String name) {
        super(name);
    }

    protected void subscribe(Product product, Client client) {
        if (!subscribers.containsKey(product)) {
            subscribers.put(product, new HashSet<>());
        }
        subscribers.get(product).add(client);
    }

    protected void getNewBid(Client client, Product product, double price) {
        subscribe(product, client);
        product.newBid(price, client);
    }

    protected void notify(Product product, String notification) {
        if (subscribers.containsKey(product)) {
            for (Client client : subscribers.get(product)) {
                client.update(notification);
            }
        }
    }

    protected void sell(Product product) {
        product.updateState(State.SOLD);
        product.getBestBidder().update("You just got " + product);
    }
}
