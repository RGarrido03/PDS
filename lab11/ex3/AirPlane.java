package ex3;

public class AirPlane {

    protected AirTrafficMediator airTrafficMediator;

    public AirPlane(AirTrafficMediator airTrafficMediator) {
        this.airTrafficMediator = airTrafficMediator;
    }

    public void sendMessage(String message) {
        airTrafficMediator.notify(this, message);
    }

    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }

}
