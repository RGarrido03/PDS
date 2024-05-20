package ex3;

public class Main {
    public static void main(String[] args) {

        AirTrafficMediator mediator = new AirTrafficMediator();
        AirPlane plane1 = new AirPlane(mediator);
        AirPlane plane2 = new AirPlane(mediator);

        mediator.addAirPlane(plane1);
        mediator.addAirPlane(plane2);

        plane1.sendMessage("Import Message");
        plane2.sendMessage("Import Message2");

    }
}