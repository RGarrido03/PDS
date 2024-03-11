package lab03.Voos.src;

import java.util.ArrayList;
import java.util.List;

public class FlightData {
    public Plane plane;
    public Flight flight;
    public List<Reservation> reservation;

    public FlightData() {
        this.plane = new Plane();
        this.flight = new Flight();
        this.reservation = new ArrayList<>();
    }
}