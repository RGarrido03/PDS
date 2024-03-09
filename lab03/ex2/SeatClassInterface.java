package lab3.ex2;

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
}
