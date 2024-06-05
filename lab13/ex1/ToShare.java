package ex1;

import ex1.products.Product;
import ex1.productsReader.ProductsReader;

import java.util.*;
import java.util.function.Consumer;

public class ToShare implements Iterable<Product> {
    Map<Product, Client> products = new HashMap<>();
    private ProductsReader productsReader;

    public List<Product> getProducts() {
        return products.keySet().stream().toList();
    }

    public void add(Product product) {
        products.put(product, null);
    }

    public void remove(String code) {
        products.keySet().stream().filter(p -> p.code().equals(code)).findFirst().ifPresent(products::remove);
    }

    public void remove(Product product) {
        products.remove(product);
    }

    public void share(Product product, Client client) {
        if (!products.containsKey(product)) {
            throw new IllegalArgumentException("Product not found");
        }

        if (products.get(product) != null) {
            throw new IllegalArgumentException("Product already shared");
        }
        products.put(product, client);
    }

    public void share(String code, Client client) {
        Product product = products.keySet().stream().filter(p -> p.code().equals(code)).findFirst().orElse(null);

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        if (products.get(product) != null) {
            throw new IllegalArgumentException("Product already shared");
        }
        products.put(product, client);
    }

    public void giveBack(Product product) {
        if (!products.containsKey(product)) {
            throw new IllegalArgumentException("Product not found");
        }

        if (products.get(product) == null) {
            throw new IllegalArgumentException("Product not shared");
        }
        products.put(product, null);
    }

    public void giveBack(String code) {
        Product product = products.keySet().stream().filter(p -> p.code().equals(code)).findFirst().orElse(null);

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        if (products.get(product) == null) {
            throw new IllegalArgumentException("Product not shared");
        }
        products.put(product, null);
    }

    public String sharedProducts() {
        return products.entrySet().stream().filter(e -> e.getValue() != null)
                       .map(e -> e.getKey().code()).toList().toString();
    }

    public List<Product> totalProducts() {
        return products.keySet().stream().toList();
    }

    public void setProductsReader(ProductsReader productsReader) {
        this.productsReader = productsReader;
    }

    public void importProductsFromProductsReader() {
        this.productsReader.getItems().forEach(product -> products.put(product, null));
    }

    @Override
    public Iterator<Product> iterator() {
        return products.keySet().iterator();
    }

    @Override
    public void forEach(Consumer<? super Product> action) {
        products.keySet().forEach(action);
    }

    @Override
    public Spliterator<Product> spliterator() {
        return products.keySet().spliterator();
    }
}
