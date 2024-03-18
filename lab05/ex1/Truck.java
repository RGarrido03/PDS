package ex1;

public class Truck extends Vehicle {
    private final String vin;
    private final int weight, maxLoad;

    Truck(String licensePlate, String brand, String model, int power, String vin, int weight, int maxLoad) {
        super(licensePlate, brand, model, power);
        this.vin = vin;
        this.weight = weight;
        this.maxLoad = maxLoad;
    }

    @Override
    public String toString() {
        return "Truck: " + super.toString() + ", VIN: " + this.vin + ", weight: " + this.weight + ", max load: " + this.maxLoad;
    }
}
