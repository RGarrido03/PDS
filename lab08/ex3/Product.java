package ex3;

public abstract class Product {
    public static int indentation = 0;

    private final String name;
    private final double weight;

    protected Product(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " '" + name + "' - Weight: " + weight;
    }
}
