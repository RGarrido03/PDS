package lab01;

import java.io.PrintWriter;
import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();

    public static char[][] initializePuzzle(int size) {
        char[][] puzzle = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                puzzle[i][j] = '.';
            }
        }
        return puzzle;
    }

    public static void printToConsoleAndFile(String str, PrintWriter writer) {
        writer.println(str);
        System.out.println(str);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Direction randomDirection() {
        int x = RANDOM.nextInt(Direction.values().length);
        return Direction.values()[x];
    }
}
