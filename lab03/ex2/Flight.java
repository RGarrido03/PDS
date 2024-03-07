package lab3.ex2;

import lab3.ex2.utils.Error;

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
        return this.reservations;
    }

    @Override
    public Reservation getReservation(int id) {
        return this.reservations.get(id);
    }

    @Override
    public Reservation addReservation(ClassType type, int seats) throws IllegalArgumentException {
        SeatClassInterface seatClass = switch (type) {
            case EXECUTIVE -> this.plane.getExecutive();
            case TOURISTIC -> this.plane.getTouristic();
        };

        if (seatClass.getAvailableSeats() < seats) {
            throw new IllegalArgumentException(Error.NO_SEATS_AVALIABLE.toString());
        }

        // TODO: Add the seat logic

        return new Reservation(0, type, List.of());
    }

    @Override
    public void cancelReservation(int id) {
        if (id < 0 || id >= this.reservations.size()) {
            throw new IllegalArgumentException(Error.INVALID_RESERVATION_CODE.toString());
        }

        Reservation reservation = this.reservations.get(id);

        SeatClassInterface seats = switch (reservation.type()) {
            case EXECUTIVE -> this.plane.getExecutive();
            case TOURISTIC -> this.plane.getTouristic();
        };

        for (Integer[] seat : reservation.seats()) {
            seats.emptySeat(seat[0], seat[1]);
        }

        this.reservations.remove(id);
    }
}
