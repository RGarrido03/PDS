package ex1;

public class Main {
    public static void main(String[] args) {

        //Manager
        Manager m1 = new Manager("Garrido");
        //Products
        Product p1 = new Product("S&P 500", 500, m1);
        Product p2 = new Product("Shampoo", 56, m1);
        Product p3 = new Product("Car", 80, m1);
        Product p4 = new Product("Gold", 50, m1);
        Product p5 = new Product("Silver", 20, m1);
        //Clients
        Client c1 = new Client("Bruno");
        Client c2 = new Client("Lopes");
        Client c3 = new Client("Ruben");

        //Subscriptions
        m1.subscribe(p1, c2);
        System.out.println("Subscribed to " + p1 + ": " +c2.getName());
        m1.subscribe(p2, c3);
        System.out.println("Subscribed to " + p2 + ": " +c3.getName());
        m1.subscribe(p5, c3);
        System.out.println("Subscribed to " + p2 + ": " +c3.getName());

        



    }
}
