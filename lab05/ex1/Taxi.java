package ex1;

public class Taxi extends Car {
    private final String licenseNumber;

    Taxi(String licensePlate, String brand, String model, int power, String vin, int trunkCapacity, String licenseNumber) {
        super(licensePlate, brand, model, power, vin, trunkCapacity);
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "Taxi: " + super.toString() + ", license number: " + this.licenseNumber;
    }
}
