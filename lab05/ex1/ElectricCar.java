package ex1;

public class ElectricCar extends Car {
    private final int autonomy;
    private final int year;

    ElectricCar(String licensePlate, String brand, String model, int power, String vin, int trunkCapacity, int autonomy, int year) {
        super(licensePlate, brand, model, power, vin, trunkCapacity);
        this.autonomy = autonomy;
        this.year = year;
    }

    @Override
    public String toString() {
        return super.toString() + ", battery: " + this.autonomy + ", year: " + this.year;
    }
}
