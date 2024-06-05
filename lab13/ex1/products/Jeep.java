package ex1.products;

public class Jeep implements Product {
    private final OldJeep adaptee;

    public Jeep(OldJeep adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public String code() {
        return splitData()[0];
    }

    @Override
    public String description() {
        return splitData()[1];
    }

    @Override
    public double points() {
        return Double.parseDouble(splitData()[1]);
    }

    private String[] splitData() {
        return adaptee.getData().split(";");
    }
}
