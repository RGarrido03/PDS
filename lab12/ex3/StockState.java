package ex3;

public class StockState extends State {
    public StockState(Book book) {
        super("Inventário", book);
    }

    @Override
    public void register() {
        super.getBook().changeState(new AvailableState(super.getBook()));
    }
}
