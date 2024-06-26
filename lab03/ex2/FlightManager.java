package lab3.ex2;

import lab3.ex2.utils.Error;
import lab3.ex2.utils.ScannerParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FlightManager {
    private static final Map<String, Flight> flights = new HashMap<>();
    private static Scanner sc;

    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                System.setIn(new FileInputStream(args[0]));
            } catch (FileNotFoundException e) {
                System.err.println(Error.FILE_NOT_FOUND);
                System.exit(1);
            }
        }

        sc = new Scanner(System.in);

        if (args.length == 0) {
            System.out.println("Choose an option (H for help): ");
        }

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
            } catch (FileNotFoundException e) {
                System.err.println("File not found. Check the path: " + System.getProperty("user.dir"));
            } finally {
                if (sc.hasNextLine()) {
                    sc.nextLine();
                }
                if (args.length == 0) {
                    System.out.println("\nChoose an option (H for help): ");
                }
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

    private static void flightInfo() throws FileNotFoundException {
        String filename = ScannerParser.parseRegexString(sc, ".+\\.txt", Error.INVALID_FILENAME);

        Scanner sc2 = new Scanner(new File("ex2/" + filename));

        String flightCodeWithChevron = ScannerParser.parseRegexString(sc2, ">[A-Z0-9]+", Error.INVALID_FLIGHT_CODE);
        String flightCode = flightCodeWithChevron.substring(1);

        if (flights.containsKey(flightCode)) {
            throw new IllegalArgumentException(Error.FLIGHT_ALREADY_EXISTS.toString());
        }

        String first = ScannerParser.parseRegexString(sc2, "\\d+x\\d+", Error.INVALID_NUMBER_OF_SEATS);
        String second = ScannerParser.parseRegexString(sc2, "\\d+x\\d+", Error.INVALID_NUMBER_OF_SEATS, true);

        String touristicLayout, executiveLayout;
        if (second == null) {
            touristicLayout = first;
            executiveLayout = null;
        } else {
            executiveLayout = first;
            touristicLayout = second;
        }
        Flight flight = new Flight(flightCode, new Plane(touristicLayout, executiveLayout));

        System.out.println("Flight code " + flightCode + ". ");
        System.out.println(flight.getPlane().getTouristic().getCapacity() + " Touristic seats.");

        if (flight.getPlane().getExecutive() != null) {
            System.out.println(flight.getPlane().getExecutive().getCapacity() + " Executive seats.");
        } else {
            System.out.println("No Executive seats in this flight.");
        }

        while (sc2.hasNextLine()) {
            ClassType type = ScannerParser.parseClassType(sc2);
            int seats = ScannerParser.parseInt(sc2, Error.INVALID_NUMBER_OF_SEATS);

            try {
                flight.addReservation(type, seats);
            } catch (IllegalArgumentException e) {
                System.err.println("It's not possible to add a reservation for " + type + " " + seats);
                return;
            }
        }

        flights.put(flightCode, flight);
    }

    private static void flightReservations() {
        String flightCode = ScannerParser.parseRegexString(sc, "[A-Z0-9]+", Error.INVALID_FLIGHT_CODE);

        if (!flights.containsKey(flightCode)) {
            throw new IllegalArgumentException(Error.FLIGHT_NOT_FOUND.toString());
        }

        flights.get(flightCode).printReservations();
    }

    private static void addFlight() {
        String flightCode = ScannerParser.parseRegexString(sc, "[A-Z0-9]+", Error.INVALID_FLIGHT_CODE);

        if (flights.containsKey(flightCode)) {
            throw new IllegalArgumentException(Error.FLIGHT_ALREADY_EXISTS.toString());
        }

        String touristicLayout = ScannerParser.parseRegexString(sc, "\\d+x\\d+", Error.INVALID_NUMBER_OF_SEATS);

        if (!sc.hasNext()) {
            flights.put(flightCode, new Flight(flightCode, new Plane(touristicLayout)));
            return;
        }

        String executiveLayout = ScannerParser.parseRegexString(sc, "\\d+x\\d+", Error.INVALID_NUMBER_OF_SEATS);
        flights.put(flightCode, new Flight(flightCode, new Plane(touristicLayout, executiveLayout)));
    }

    private static void newReservation() {
        String flightCode = ScannerParser.parseRegexString(sc, "[A-Z0-9]+", Error.INVALID_FLIGHT_CODE);

        if (!flights.containsKey(flightCode)) {
            throw new IllegalArgumentException(Error.FLIGHT_NOT_FOUND.toString());
        }
        Flight flight = flights.get(flightCode);
        ClassType type = ScannerParser.parseClassType(sc);
        int seats = ScannerParser.parseInt(sc, Error.INVALID_NUMBER_OF_SEATS);

        Reservation reservation = flight.addReservation(type, seats);
        System.out.printf("%s:%s\n", flightCode, reservation);
    }

    private static void cancelReservation() {
        String[] reservationCode =
                ScannerParser.parseRegexString(sc, "[A-Z0-9]+:\\d+", Error.INVALID_RESERVATION_CODE).split(":");
        String flightCode = reservationCode[0];

        if (!flights.containsKey(flightCode)) {
            throw new IllegalArgumentException(Error.FLIGHT_NOT_FOUND.toString());
        }

        int reservation = Integer.parseInt(reservationCode[1]);
        flights.get(flightCode).cancelReservation(reservation);
    }
}
