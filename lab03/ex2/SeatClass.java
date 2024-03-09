package lab3.ex2;

import lab3.ex2.utils.Error;

import java.util.Arrays;

public class SeatClass implements SeatClassInterface {
    private final ClassType type;
    private final int[][] seats;

    public SeatClass(ClassType type, int rows, int seatsPerRow) {
        this.type = type;
        this.seats = new int[rows][seatsPerRow];
    }

    @Override
    public ClassType getType() {
        return this.type;
    }

    @Override
    public int[][] getSeats() {
        return this.seats;
    }

    @Override
    public int getAvailableSeats() {
        int count = 0;
        for (int[] row : this.seats) {
            for (int seat : row) {
                if (seat == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int getRows() {
        return this.seats.length;
    }

    @Override
    public boolean isRowAvailable(int row) {
        if (row < 0 || row >= this.getRows()) {
            throw new IndexOutOfBoundsException(Error.INVALID_ROW.toString());
        }
        return Arrays.stream(this.seats[row]).allMatch(seat -> isSeatAvailable(row, seat));
    }

    @Override
    public int getFirstAvailableRow() {
        for (int row = 0; row < this.getRows(); row++) {
            if (isRowAvailable(row)) {
                return row;
            }
        }
        return -1;
    }

    @Override
    public int getSeatsPerRow() {
        return this.seats[0].length;
    }

    @Override
    public int getSeat(int row, int seat) {
        return this.seats[row][seat];
    }

    @Override
    public boolean isSeatAvailable(int row, int seat) {
        return seats[row][seat] == 0;
    }

    @Override
    public void setSeatId(int row, int seat, int id) {
        seats[row][seat] = id;
    }

    @Override
    public void emptySeat(int row, int seat) {
        seats[row][seat] = 0;
    }
}
