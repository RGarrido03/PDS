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
        // Seats per row will be lines in the table
        // Rows will be columns in the table
        int[] size = getPrintTableSize();
        int seatsPerRow = size[0];
        int rows = size[1];

        SeatClass executive = this.plane.getExecutive();
        SeatClass touristic = this.plane.getTouristic();

        // Print header
        System.out.print(" ");
        for (int row = 0; row < rows; row++) {
            System.out.printf(" %2d", row);
        }
        System.out.println();

        // Print seats
        for (int seatInRow = 0; seatInRow < seatsPerRow; seatInRow++) {
            StringBuilder builder = new StringBuilder();

            builder.append((char) ('A' + seatInRow));

            if (executive != null) {
                for (int row = 0; row < executive.getRows(); row++) {
                    if (seatInRow < executive.getSeatsPerRow()) {
                        builder.append(String.format(" %2d", executive.getSeat(row, seatInRow)));
                    } else {
                        builder.append("   ");
                    }
                }
            }

            for (int row = 0; row < touristic.getRows(); row++) {
                if (seatInRow < touristic.getSeatsPerRow()) {
                    builder.append(String.format(" %2d", touristic.getSeat(row, seatInRow)));
                } else {
                    builder.append("   ");
                }
            }

            System.out.println(builder);
        }
    }

    private int[] getPrintTableSize() {
        int seatsPerRow = 0, rows = 0;
        SeatClass executive = this.plane.getExecutive();

        if (executive != null) {
            seatsPerRow += executive.getSeatsPerRow();
            rows += executive.getRows();
        }

        SeatClass touristic = this.plane.getTouristic();
        seatsPerRow = touristic.getSeatsPerRow() > seatsPerRow ? touristic.getSeatsPerRow() : seatsPerRow;
        rows += touristic.getRows();
        return new int[]{seatsPerRow, rows};
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

        if (seatClass == null) {
            throw new IllegalArgumentException(Error.EXECUTIVE_CLASS_NOT_AVALIABLE.toString());
        }

        if (seatClass.getAvailableSeats() < seats) {
            throw new IllegalArgumentException(Error.NO_SEATS_AVALIABLE.toString());
        }

        int id = this.reservations.size() + 1; // IDs must start at 1 and not 0
        List<Integer[]> seatsL = seatClass.setSeats(seats, id);
        Reservation reservation = new Reservation(id, type, seatsL);
        reservations.add(reservation);
        return reservation;
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
