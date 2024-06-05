package ex1.factories;

import ex1.products.Car;
import ex1.products.Product;

public class CarFactory implements ProductFactory {
    @Override
    public Product createProduct(String code, String description, int points) {
        return new Car(code, description, points);
    }
}
