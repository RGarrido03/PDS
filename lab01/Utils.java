package lab01;

import java.io.PrintWriter;

public class Utils {
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
}
