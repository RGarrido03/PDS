package lab03.Voos.src;

import java.util.List;

public class FlightCreate {
    public static void createNewFlight(String flightCode, String seatExec, String seatTurist, List<FlightData> flightDataList) {
        FlightData newFlightData = new FlightData();

        newFlightData.flight.setFlightCode(flightCode);
        newFlightData.plane.setSeatExec(seatExec);
        newFlightData.plane.setSeatTurist(seatTurist);

        flightDataList.add(newFlightData);
    }
}