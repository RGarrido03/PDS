package ex3;

public abstract class State {
    private final String name;
    private final Book book;
    protected String NOT_AVAILABLE_MESSAGE = "Operation not available";

    protected State(String name, Book book) {
        this.name = name;
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public Book getBook() {
        return book;
    }

    public void register() {
        System.err.println(NOT_AVAILABLE_MESSAGE);
    }

    public void request() {
        System.err.println(NOT_AVAILABLE_MESSAGE);
    }

    public void giveBack() {
        System.err.println(NOT_AVAILABLE_MESSAGE);
    }

    public void reserve() {
        System.err.println(NOT_AVAILABLE_MESSAGE);
    }

    public void cancelReservation() {
        System.err.println(NOT_AVAILABLE_MESSAGE);
    }


    @Override
    public String toString() {
        return name;
    }
}
