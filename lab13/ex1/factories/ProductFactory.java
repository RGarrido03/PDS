package ex1.factories;

import ex1.products.Product;

public interface ProductFactory {
    Product createProduct(String code, String description, int points);
}
