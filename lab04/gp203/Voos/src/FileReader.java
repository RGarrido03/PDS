// AUTHORS:
// 104341 Diogo Silva
// 103320 Bruno Gomes
package lab03.Voos.src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {
    public static void readerFile(String filename, Plane plane, Flight flight, List<Reservation> reservation){
        try{
            File wordFile = new File(filename);
            Scanner reader = new Scanner(wordFile);
            if(!reader.hasNextLine()){
                System.out.println("Error: File is empty");
            }
            int line = 0;
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                String[] words = data.split(" ");
                emptyLine(data);
                if(line == 0){ // Line with flight number and class seats
                    if(data.charAt(0) != '>'){
                        System.out.println("Error: File does not start with a '>'");
                    }else{
                        flight.setFlightCode(words[0].substring(1)); // remove the '>'
                        if(words.length < 2){
                            System.out.println("Error: Class seats not found");
                        }else if(words.length == 2){ //only turist seats
                            plane.setSeatTurist(words[1]);
                        }else{
                            plane.setSeatExec(words[1]);
                            plane.setSeatTurist(words[2]);
                        }
                    }
                }else{ // Line with the reservations
                    Reservation newReservation = new Reservation();
                    newReservation.setClassType(words[0]);
                    newReservation.setPassengerNumber(Integer.parseInt(words[1]));
                    reservation.add(newReservation);
                }
                line++;
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error has occured");
            e.printStackTrace();
        }   
    }
    public static void emptyLine(String word){
        if(word.isEmpty()){
            System.out.println("Error: File has an empty line");
        }
    }
}
