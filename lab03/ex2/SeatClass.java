package lab3.ex2;

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
        return 0;
    }

    @Override
    public boolean isSeatAvailable(int row, int seat) {
        return false;
    }

    @Override
    public void setSeatId(int row, int seat, int id) {

    }

    @Override
    public void emptySeat(int row, int seat) {

    }
}
