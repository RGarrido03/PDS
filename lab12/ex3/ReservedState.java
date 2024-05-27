package ex3;

public class ReservedState extends State {
    public ReservedState(Book book) {
        super("Reservado", book);
    }

    @Override
    public void cancelReservation() {
        super.getBook().changeState(new AvailableState(super.getBook()));
    }
}
