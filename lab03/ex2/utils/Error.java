package lab3.ex2.utils;

public enum Error {
    INVALID_OPTION("Invalid option"),
    INVALID_FILENAME("Invalid filename"),
    INVALID_FLIGHT_CODE("Invalid flight code"),
    INVALID_NUMBER_OF_SEATS("Invalid number of seats"),
    INVALID_CLASS_TYPE("Invalid class type"),
    RESERVATION_NOT_FOUND("Reservation not found"),
    FLIGHT_NOT_FOUND("Flight not found"),
    NO_SEATS_AVALIABLE("No seats available"),
    FLIGHT_ALREADY_EXISTS("Flight already exists"),
    EXECUTIVE_CLASS_NOT_AVALIABLE("Executive class is not available for this flight"),
    ;

    private final String message;

    Error(String s) {
        this.message = s;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
