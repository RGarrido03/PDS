package ex1;

public class Bus extends Vehicle {
    private final String vin;
    private final int weight, maxPassengers;

    Bus(String licensePlate, String brand, String model, int power, int weight, String vin, int maxPassengers) {
        super(licensePlate, brand, model, power);
        this.vin = vin;
        this.weight = weight;
        this.maxPassengers = maxPassengers;
    }

    @Override
    public String toString() {
        return "Bus: " + super.toString() + ", VIN: " + this.vin + ", weight: " + this.weight + ", max passengers: " + this.maxPassengers;
    }
}
