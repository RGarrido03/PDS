package ex1.productsReader;

import ex1.factories.CarFactory;
import ex1.factories.MotorcycleFactory;
import ex1.factories.ProductFactory;
import ex1.factories.VanFactory;
import ex1.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductsReaderFromTXTFile implements ProductsReader {
    private final Scanner scanner;

    public ProductsReaderFromTXTFile(String file) {
        this.scanner = new Scanner(file);
        this.scanner.useDelimiter("[\n\r\t]");
    }

    @Override
    public List<Product> getItems() {
        List<Product> products = new ArrayList<>();
        ProductFactory factory;

        while (scanner.hasNext()) {
            factory = switch (scanner.next()) {
                case "Car" -> new CarFactory();
                case "Van" -> new VanFactory();
                case "Motorcycle" -> new MotorcycleFactory();
                default -> throw new IllegalArgumentException("Not supported product");
            };

            products.add(factory.createProduct(scanner.next(), scanner.next(), scanner.nextInt()));
        }

        return products;
    }
}
