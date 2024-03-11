package lab03.Voos.src;
import java.util.*;

public class FlightManager{
    public static final Scanner sc = new Scanner(System.in);
    public static List<FlightData> flightDataList = new ArrayList<>();
    public static void main(String args[]) {
        String inputFile = null;
        System.out.println("Choose an option (H for help)");
        String input;
        do{
            input = sc.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : null;
            
            switch(command){
                case "H":
                    helper();
                    break;
                case "I":
                    if (argument != null) {
                        inputFile = "lab03/Voos/examples/" + argument;
                        FlightData flightData = new FlightData();
                        flightDataList.add(flightData);
                        FileReader.readerFile(inputFile, flightData.plane, flightData.flight, flightData.reservation);
                        System.out.print(flightData.flight.getFlightCode() + " flight data inserted\n");
                    }else{
                        System.out.println("Please Input a flight data file\n");
                    }
                    break;
                case "M":
                    if(argument != null){
                        if(flightDataList.size() == 0){
                            System.out.println("No Flight Data inserted yet\n");
                            break;
                        }
                        for(int i = 0; i < flightDataList.size(); i++){
                            if(argument.equals(flightDataList.get(i).flight.getFlightCode())){
                                FlightMap.showMap(flightDataList.get(i).plane, flightDataList.get(i).flight, flightDataList.get(i).reservation);
                                break;
                            }
                        }
                    }else{
                        System.out.println("Pleas Input a flight Code\n");
                    }    
                    break;
                case "F":
                    handleCreateCommand(argument, flightDataList);
                    break;
                case "R":
                        //não completei o comando "R" porque só estava a fazer o mapa no "M" não tenho tempo para dar refactor
                    break;
                case "C":
                    handleCancelCommand(argument, flightDataList);
                    for(int i = 0; i< flightDataList.size(); i++){
                        System.out.println(flightDataList.get(i).flight.getFlightCode());
                        for(int j = 0; j < flightDataList.get(i).reservation.size(); j++){
                            System.out.println(flightDataList.get(i).reservation.get(j).getClassType() + " " + flightDataList.get(i).reservation.get(j).getPassengerNumber());
                        }
                    }
                    
                    break;

                default:
                    System.out.println("Invalid Command\n(H for help)");
            }
        }while(!"Q".equals(input));
    }

    public static void helper(){
        System.out.println("I <filename> : Reads a flight data file\nM <flightCode> : Shows a reservation flight map ");
        System.out.println("F <FC> <num_seats_exec> <num_seats_turist> : Creates a new flight with turist seats and optional exec seats");
        System.out.println("R <FC> <class> <num_seats> : Reserves a number of seats in a flight class");
        System.out.println("C <FC:reservation_number> : Cancels a reservation ");
        System.out.println("Q : Program end");
    }

    public static void handleCancelCommand(String argument, List<FlightData> flightDataList) {
        if (argument != null) {
            String[] parts = argument.split(":");
            if (parts.length == 2) {
                String flightCode = parts[0];
                int reservationNumber = Integer.parseInt(parts[1]);
                for (FlightData fd : flightDataList) {
                    if (fd.flight.getFlightCode().equals(flightCode)) {
                        FlightCancelation.cancelReservation(fd, reservationNumber);
                        System.out.println("Reservation " + reservationNumber + " cancelled for flight " + flightCode);
                        break;
                    }
                }
            }
        } else {
            System.out.println("Please input a flight code and reservation number");
        }
    }

    public static void handleCreateCommand(String argument, List<FlightData> flightDataList) {
        if (argument != null) {
            String[] parts = argument.split(" ");
            if (parts.length == 3) {
                String flightCode = parts[0];
                String numSeatsExec = parts[1];
                String numSeatsTourist = parts[2];
                FlightCreate.createNewFlight(flightCode, numSeatsExec, numSeatsTourist, flightDataList);
                System.out.println("New flight " + flightCode + " created");
            }
        } else {
            System.out.println("Please input a flight code and number of seats");
        }
    }

}