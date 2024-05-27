package ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void register(int id) {
        books.get(id).register();
    }

    public void request(int id) {
        books.get(id).request();
    }

    public void giveBack(int id) {
        books.get(id).giveBack();
    }

    public void reserve(int id) {
        books.get(id).reserve();
    }

    public void cancelReservation(int id) {
        books.get(id).cancelReservation();
    }

    @Override
    public String toString() {
        return "*** Library ***\n" + books.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }
}
