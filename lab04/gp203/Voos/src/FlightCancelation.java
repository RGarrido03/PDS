package lab03.Voos.src;

public class FlightCancelation {
    public static void cancelReservation(FlightData flightData, int reservationNumber){
        if (reservationNumber > 0 && reservationNumber <= flightData.reservation.size()) {
            flightData.reservation.remove(reservationNumber - 1);
        }
    }
}