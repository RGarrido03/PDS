package ex1.productsReader;

import ex1.products.Car;
import ex1.products.Motorcycle;
import ex1.products.Product;
import ex1.products.Van;

import java.util.List;

public class ProductsReaderHardcodedProducts implements ProductsReader {
    @Override
    public List<Product> getItems() {
        return List.of(new Product[] {
            new Car("ZA11ZB", "Tesla, Grey, 2021", 100),
            new Van("AA22BB", "Chevrolet Chevy, 2020", 180),
            new Motorcycle("ZA33ZB", "Touring, 750, 2022", 85),
            new Car("BB44ZB", "Ford Mustang, Red, 2021", 150),
        });
    }
}
