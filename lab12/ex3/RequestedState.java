package ex3;

public class RequestedState extends State {
    public RequestedState(Book book) {
        super("Emprestado", book);
    }

    @Override
    public void giveBack() {
        super.getBook().changeState(new AvailableState(super.getBook()));
    }
}
