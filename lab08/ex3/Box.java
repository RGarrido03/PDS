package ex3;

import java.util.ArrayList;
import java.util.List;

public class Box extends Product {
    private final List<Product> products = new ArrayList<>();

    public Box(String name, double weight) {
        super(name, weight);
    }

    public void add(Product product) {
        products.add(product);
    }

    public void draw() {
        System.out.println(this);
    }

    public double getIndividualWeight() {
        return super.getWeight();
    }

    @Override
    public double getWeight() {
        return products.stream().reduce(super.getWeight(), (acc, product) -> acc + product.getWeight(), Double::sum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("* Box '")
          .append(super.getName())
          .append("' [Weight: ")
          .append(getIndividualWeight())
          .append("; Total: ")
          .append(getWeight())
          .append("]\n");

        if (!products.isEmpty()) {
            Product.indentation++;
        }

        for (Product product : products) {
            sb.append("\t".repeat(Product.indentation))
              .append(product.toString())
              .append("\n");
        }

        if (!products.isEmpty()) {
            Product.indentation--;
        }

        return sb.toString().stripTrailing();
    }
}
