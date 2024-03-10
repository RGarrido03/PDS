package lab3.ex2;

import java.util.List;

public interface SeatClassInterface {
    /**
     * Get the type of the class.
     *
     * @return Class type.
     */
    ClassType getType();

    /**
     * Get the seat grid.
     *
     * @return Seats array.
     */
    int[][] getSeats();

    /**
     * Get how many available seats are there.
     *
     * @return Number of available seats.
     */
    int getAvailableSeats();

    /**
     * Get the number of seat rows.
     *
     * @return Number of rows.
     */
    int getRows();

    /**
     * Get if a row is fully available.
     *
     * @param row Row number.
     * @return Whether the row is available or not.
     */
    boolean isRowAvailable(int row) throws IndexOutOfBoundsException;

    /**
     * Get the first fully available row.
     *
     * @return Row number. -1 if not found.
     */
    int getFirstAvailableRow();

    /**
     * Get the number of seats per row.
     *
     * @return Number of seats per row.
     */
    int getSeatsPerRow();

    /**
     * Get a seat's reservation ID.
     *
     * @param row  Row number.
     * @param seat Seat number, i.e., the column in the grid.
     * @return Reservation ID.
     */
    int getSeat(int row, int seat);

    /**
     * Check if a seat is available.
     *
     * @param row  Row number.
     * @param seat Seat number, i.e., the column in the grid.
     * @return Whether the seat is available or not.
     */
    boolean isSeatAvailable(int row, int seat);

    /**
     * Set a seat to an ID (typically, the reservation one).
     *
     * @param row  Row number.
     * @param seat Seat number, i.e., the column in the grid.
     * @param id   Target ID.
     */
    void setSeatId(int row, int seat, int id);

    /**
     * Set a seat as empty (aka 0).
     *
     * @param row  Row number.
     * @param seat Seat number, i.e., the column in the grid.
     */
    void emptySeat(int row, int seat);

    /**
     * Get a random available coordinate.
     *
     * @return Seat coordinates. Null if not found.
     */
    Integer[] getRandomAvailableSeat();

    /**
     * Given the number of requested seats, get coordinates for each seat, prefeberally in full rows.
     *
     * @param seats         Number of seats.
     * @param reservationId Reservation ID.
     * @return List of seat coordinates.
     */
    List<Integer[]> setSeats(int seats, int reservationId);

    /**
     * Given the number of requested seats, get coordinates for each seat, without a criteria.
     * <p>
     * Helper function for <code>setSeats</code>.
     *
     * @param seats Number of seats.
     * @return List of seat coordinates.
     * @throws IllegalArgumentException If there are no seats available.
     */
    List<Integer[]> setSeatsRandomly(int seats) throws IllegalArgumentException;
}
