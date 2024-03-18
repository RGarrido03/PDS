package ex1;

public class Car extends Vehicle {
    private final String vin;
    private final int trunkCapacity;

    Car(String licensePlate, String brand, String model, int power, String vin, int trunkCapacity) {
        super(licensePlate, brand, model, power);
        this.vin = vin;
        this.trunkCapacity = trunkCapacity;
    }

    @Override
    public String toString() {
        return "Car: " + super.toString() + ", VIN: " + this.vin + ", trunk capacity: " + this.trunkCapacity;
    }
}