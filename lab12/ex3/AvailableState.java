package ex3;

public class AvailableState extends State {
    public AvailableState(Book book) {
        super("Disponível", book);
    }

    @Override
    public void reserve() {
        super.getBook().changeState(new ReservedState(super.getBook()));
    }

    @Override
    public void request() {
        super.getBook().changeState(new RequestedState(super.getBook()));
    }
}
