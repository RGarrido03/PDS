package ex1;

public class ElectricBus extends Bus {
    private final int autonomy;
    private final int year;

    ElectricBus(String licensePlate, String brand, String model, int power, int weight, String vin, int maxPassengers, int autonomy, int year) {
        super(licensePlate, brand, model, power, weight, vin, maxPassengers);
        this.autonomy = autonomy;
        this.year = year;
    }

    @Override
    public String toString() {
        return super.toString() + ", battery: " + this.autonomy + ", year: " + this.year;
    }
}
