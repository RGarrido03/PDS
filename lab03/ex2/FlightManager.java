package lab3.ex2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FlightManager {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, Flight> flights = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Choose an option (H for help): ");

        while (sc.hasNextLine()) {
            switch (sc.next()) {
                case "H" -> help();
                case "I" -> flightInfo();
                case "M" -> flightBookings();
                case "F" -> addFlight();
                case "R" -> newBooking();
                case "C" -> cancelBooking();
                case "Q" -> System.exit(0);
                default -> System.out.println("Invalid option");
            }

            System.out.println("Choose an option (H for help): ");
        }
    }

    private static void help() {
        System.out.println("""
                                   Available options:
                                   I - Flight info
                                   M - Flight bookings
                                   F - Add flight
                                   R - New booking
                                   C - Cancel booking
                                   Q - Quit
                                   """);
    }

    private static void flightInfo() {
        System.out.println("Flight info");
    }

    private static void flightBookings() {
        System.out.println("Flight bookings");
    }

    private static void addFlight() {
        System.out.println("Add flight");
    }

    private static void newBooking() {
        System.out.println("New booking");
    }

    private static void cancelBooking() {
        System.out.println("Cancel booking");
    }
}
