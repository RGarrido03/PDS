package lab3.ex2;

import java.util.List;

public interface FlightInterface {
    /**
     * ID getter.
     *
     * @return ID.
     */
    String getId();

    /**
     * Plane getter.
     *
     * @return Plane object.
     */
    Plane getPlane();

    /**
     * Print the reservation map.
     */
    void printReservations();

    /**
     * Get reservations.
     *
     * @return List of reservations.
     */
    List<Reservation> getReservations();

    /**
     * Get a reservation through its ID.
     *
     * @param id Reservation ID.
     * @return Reservation object.
     */
    Reservation getReservation(int id);

    /**
     * Add a reservation.
     *
     * @param type  Executive or touristic
     * @param seats Number of seats.
     * @return Reservation code.
     */
    Reservation addReservation(ClassType type, int seats) throws IllegalArgumentException;

    /**
     * Cancel a reservation, identified by an ID.
     *
     * @param id Reservation ID.
     */
    void cancelReservation(int id);
}
