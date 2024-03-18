package ex1;

public class Vehicle {
    private final int power;
    private final String maker, model, licensePlate;

    Vehicle(String licensePlate, String maker, String model, int power) {
        this.licensePlate = licensePlate;
        this.power = power;
        this.maker = maker;
        this.model = model;
    }

    @Override
    public String toString() {
        return this.maker + " " + this.model + ", " + this.licensePlate + ". Power: " + this.power;
    }
}
