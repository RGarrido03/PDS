package ex1.products;

public class Car implements Product {
    private final String code;
    private final String description;
    private final int points;

    public Car(String code, String description, int points) {
        this.code = code;
        this.description = description;
        this.points = points;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public double points() {
        return this.points;
    }

    @Override
    public String toString() {
        return "Car{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                '}';
    }
}
