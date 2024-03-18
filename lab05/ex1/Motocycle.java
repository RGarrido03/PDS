package ex1;

public class Motocycle extends Vehicle {
    private final MotocycleType type;

    Motocycle(String licensePlate, String brand, String model, int power, MotocycleType type) {
        super(licensePlate, brand, model, power);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Motocycle: " + super.toString() + ", type: " + this.type;
    }
}