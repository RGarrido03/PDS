package ex1.factories;

import ex1.products.Product;
import ex1.products.Van;

public class VanFactory implements ProductFactory {
    @Override
    public Product createProduct(String code, String description, int points) {
        return new Van(code, description, points);
    }
}
