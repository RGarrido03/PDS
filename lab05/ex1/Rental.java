package ex1;

import java.util.ArrayList;
import java.util.List;

public class Rental {
    private final String name, zipCode, email;
    private final List<Vehicle> stock = new ArrayList<>();

    public Rental(String name, String zipCode, String email) {
        this.name = name;
        this.zipCode = zipCode;
        this.email = email;
    }

    public List<Vehicle> getStock() {
        return this.stock;
    }

    public void addVehicle(Vehicle vehicle) {
        this.stock.add(vehicle);
    }
}
