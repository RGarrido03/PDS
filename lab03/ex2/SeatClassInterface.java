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
