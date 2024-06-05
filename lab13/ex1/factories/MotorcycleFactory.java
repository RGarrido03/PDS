package ex1.factories;

import ex1.products.Motorcycle;
import ex1.products.Product;

public class MotorcycleFactory implements ProductFactory {
    @Override
    public Product createProduct(String code, String description, int points) {
        return new Motorcycle(code, description, points);
    }
}
