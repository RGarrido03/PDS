package lab3.ex2;

import lab3.ex2.utils.Error;

import java.util.ArrayList;
import java.util.List;

public class SeatClass implements SeatClassInterface {
    private final ClassType type;
    private final int[][] seats;

    public SeatClass(ClassType type, int rows, int seatsPerRow) {
        this.type = type;
        this.seats = new int[rows][seatsPerRow];
    }

    @Override
    public int getCapacity() {
        return this.getRows() * this.getSeatsPerRow();
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
        for (int i = 0; i < this.seats[row].length; i++) {
            if (!isSeatAvailable(row, i)) {
                return false;
            }
        }
        return true;
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

    @Override
    public Integer[] getRandomAvailableSeat() {
        for (int row = 0; row < this.getRows(); row++) {
            for (int seat = 0; seat < this.getSeatsPerRow(); seat++) {
                if (isSeatAvailable(row, seat)) {
                    return new Integer[]{row, seat};
                }
            }
        }
        return null;
    }

    @Override
    public List<Integer[]> setSeats(int seats, int reservationId) {
        List<Integer[]> reservations = new ArrayList<>();
        int rowsNeeded = Math.ceilDiv(seats, this.getSeatsPerRow());
        int lastRowSeats = seats % this.getSeatsPerRow();

        for (int row = 0; row < rowsNeeded; row++) {
            int seatsNeededInRow = row == (rowsNeeded - 1) ? lastRowSeats : this.getSeatsPerRow();

            int freeRow = this.getFirstAvailableRow();
            if (freeRow == -1) {
                try {
                    reservations.addAll(this.setSeatsRandomly(seatsNeededInRow));
                } catch (IllegalArgumentException e) {
                    reservations.forEach(seat -> this.setSeatId(seat[0], seat[1], 0));
                    throw e;
                }
                continue;
            }

            for (int seat = 0; seat < seatsNeededInRow; seat++) {
                this.setSeatId(freeRow, seat, -1);
                reservations.add(new Integer[]{freeRow, seat});
            }
        }

        reservations.forEach(seat -> this.setSeatId(seat[0], seat[1], reservationId));
        return reservations;
    }

    @Override
    public List<Integer[]> setSeatsRandomly(int seats) throws IllegalArgumentException {
        List<Integer[]> reservations = new ArrayList<>();

        for (int seat = 0; seat < seats; seat++) {
            Integer[] random = this.getRandomAvailableSeat();

            if (random == null) {
                reservations.forEach(reservation -> this.setSeatId(reservation[0], reservation[1], 0));
                throw new IllegalArgumentException(Error.NO_SEATS_AVALIABLE.toString());
            }

            this.setSeatId(random[0], random[1], -1);
            reservations.add(random);
        }

        return reservations;
    }
}
