package ex3;

public class Book {
    private final String title, author;
    private final int isbn, year;
    private int id;
    private State state;

    public Book(int id, String title, String author, int isbn, int year) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.year = year;
        this.state = new StockState(this);
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void register() {
        state.register();
    }

    public void request() {
        state.request();
    }

    public void giveBack() {
        state.giveBack();
    }

    public void reserve() {
        state.reserve();
    }

    public void cancelReservation() {
        state.cancelReservation();
    }

    @Override
    public String toString() {
        return String.format("%-2d    %-20s      %-10s      [%10s]", id, title, author, state);
    }
}
