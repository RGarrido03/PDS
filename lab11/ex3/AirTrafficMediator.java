package ex3;

import java.util.ArrayList;
import java.util.List;

public class AirTrafficMediator {

    private List<AirPlane> listAirplanes = new ArrayList<>();

    public void addAirPlane(AirPlane airPlane) {
        listAirplanes.add(airPlane);
    }

    public void notify(AirPlane airPlane, String message) {
        airPlane.receiveMessage(message);

        // send to all airplanes the message
        for (AirPlane plane : listAirplanes) {
            plane.receiveMessage(message);
        }
    }
}
