package lab03.Voos.src;
import java.util.*;
public class FlightMap {
    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public static void showMap(Plane plane, Flight flight, List<Reservation> reservation){
        int[] turistSeatData = getTuristSeatData(plane);
        int[] execSeatData = getExecSeatData(plane);
        char[][] map = beginMap(turistSeatData, execSeatData);
        fillMap(map, execSeatData, '0', 0);
        fillMap(map, turistSeatData, '0', execSeatData[0]);
    
        int reservationNumber = 1;
        for (Reservation res : reservation) {
            char seatType = res.getClassType().charAt(0);
            int passengers = res.getPassengerNumber();

            int startColumn = seatType == 'E' ? 0 : execSeatData[0];
            int endColumn = seatType == 'E' ? execSeatData[0] : map[0].length;

            for (int j = startColumn; j < endColumn && passengers > 0; j++) {
                int availableSeats = 0;
                for (int i = 0; i < map.length; i++) {
                    if (map[i][j] == '0') {
                        availableSeats++;
                    }
                }

                if (availableSeats == map.length) {
                    for (int i = 0; i < map.length && passengers > 0; i++) {
                        map[i][j] = Integer.toString(reservationNumber).charAt(0);
                        passengers--;
                    }
                }
            }

            for (int j = startColumn; j < endColumn && passengers > 0; j++) {
                for (int i = 0; i < map.length && passengers > 0; i++) {
                    if (map[i][j] == '0') {
                        map[i][j] = Integer.toString(reservationNumber).charAt(0);
                        passengers--;
                    }
                }
            }

            reservationNumber++;
        }

        printMap(map);
    }
    
    public static int[] getTuristSeatData(Plane plane){
        String data = plane.getSeatTurist();
        String[] seatInfoString = data.split("x");
        int[] seatData = new int[seatInfoString.length];
        for(int i = 0; i < seatInfoString.length; i++){
            seatData[i] = Integer.parseInt(seatInfoString[i]);
        }
        return seatData;
    }

    public static int[] getExecSeatData(Plane plane){
        String data = plane.getSeatExec();
        if (data == null) {
            return new int[]{0, 0};
        }
        String[] seatInfoString = data.split("x");
        int[] seatData = new int[seatInfoString.length];
        for(int i = 0; i < seatInfoString.length; i++){
            seatData[i] = Integer.parseInt(seatInfoString[i]);
        }
        return seatData;
    }

    public static char[][] beginMap(int[] turistSeatData, int[]execSeatData) {
        char[][] map = new char[Math.max(turistSeatData[1], execSeatData[1])][turistSeatData[0] + execSeatData[0]];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], '0');
        }
        return map;
    }
    
    public static void fillMap(char[][] map, int[] seatData, char seatType, int startIndex) {
        for (int i = 0; i < map.length; i++) {
            for (int j = startIndex; j < startIndex + seatData[0]; j++) {
                if (i < seatData[1]) {
                    map[i][j] = seatType;
                } else {
                    map[i][j] = ' ';
                }
            }
        }
    }
    
    public static void printMap(char[][] map) {
        System.out.print("  ");
        for (int i = 0; i < map[0].length; i++) {
            System.out.printf("%2d ", i + 1);
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.printf("%s ", alphabet[i]);
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf(" %c ", map[i][j]);
            }
            System.out.println();
        }
    }
}
