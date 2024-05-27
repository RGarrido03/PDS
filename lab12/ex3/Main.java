package ex3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("[,\n\r\t ]");
        Library library = new Library();

        library.addBook(new Book(1, "Java Anti-Stress", "Omodionah", 123456789, 2024));
        library.addBook(new Book(2, "A Guerra dos Padrões", "Jorge Omel", 123456789, 2024));
        library.addBook(new Book(3, "A Procura da Luz", "Khumatkli", 123456789, 2024));

        while (true) {
            System.out.println(library);
            System.out.println(">> <book>, <operation: (1)register; (2)request; (3)giveBack; (4)reserve; (5)cancel>\n");
            System.out.print(">> ");

            int bookId = scanner.nextInt() - 1;

            if (bookId == -1) {
                return;
            }

            int operation = scanner.nextInt();

            switch (operation) {
                case 1 -> library.register(bookId);
                case 2 -> library.request(bookId);
                case 3 -> library.giveBack(bookId);
                case 4 -> library.reserve(bookId);
                case 5 -> library.cancelReservation(bookId);
                default -> System.err.println("Invalid operation");
            }
        }
    }
}
