package lab3.ex2;

import lab3.ex2.utils.Error;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FlightManager {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, Flight> flights = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Choose an option (H for help): ");

        while (sc.hasNextLine()) {
            try {
                switch (sc.next()) {
                    case "H" -> help();
                    case "I" -> flightInfo();
                    case "M" -> flightReservations();
                    case "F" -> addFlight();
                    case "R" -> newReservation();
                    case "C" -> cancelReservation();
                    case "Q" -> System.exit(0);
                    default -> throw new IllegalArgumentException(Error.INVALID_OPTION.toString());
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            } finally {
                sc.nextLine();
                System.out.println("\nChoose an option (H for help): ");
            }
        }
    }

    private static void help() {
        System.out.println("""
                                   Available options:
                                   I - Flight info
                                   M - Flight reservations
                                   F - Add flight
                                   R - New reservation
                                   C - Cancel reservation
                                   Q - Quit
                                   """);
    }

    private static void flightInfo() {
        System.out.println("Flight info");
    }

    private static void flightReservations() {
    }

    private static void addFlight() {
        System.out.println("Add flight");
    }

    private static void newReservation() {
        System.out.println("New booking");
    }

    private static void cancelReservation() {
        System.out.println("Cancel booking");
    }
}
