package lab3.ex2;

import java.util.ArrayList;
import java.util.List;

public class Flight implements FlightInterface {
    private final String id;
    private final Plane plane;
    private final List<Reservation> reservations = new ArrayList<>();

    public Flight(String id, Plane plane) {
        this.id = id;
        this.plane = plane;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Plane getPlane() {
        return this.plane;
    }

    @Override
    public void printReservations() {

    }

    @Override
    public List<Reservation> getReservations() {
        return List.of();
    }

    @Override
    public Reservation getReservation(int id) {
        return null;
    }

    @Override
    public Reservation addReservation(ClassType type, int seats) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void cancelReservation(int id) {

    }
}
