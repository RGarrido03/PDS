package lab01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SopaDeLetras {
    private static final int MAX_SIZE = 40;

    public static void main(String[] args) throws FileNotFoundException {
        char[][] puzzle = new char[0][0];
        String[] targetWords;
        String line;
        int lnCount = 0;
        int size = 0;

        Scanner reader = new Scanner(new FileReader("sopaDeLetras.txt"));

        while (reader.hasNextLine()) {
            line = reader.nextLine().trim();
            if (line.isEmpty()) continue;

            // Puzzle
            if (Character.isUpperCase(line.charAt(0))) {
                lnCount++;

                if (size == 0) {
                    size = line.length();
                    puzzle = new char[size][size];
                } else if (!isLineValid(line, size, lnCount)) {
                    return;
                }

                puzzle[lnCount - 1] = line.toCharArray();
            } else {
                System.out.println("Words");
            }
        }

        printPuzzle(puzzle);
        reader.close();
    }

    private static boolean isLineUpperCase(String line) {
        for (char c : line.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isLineValid(String line, int size, int lnCount) {
        if (lnCount > size || size != line.length()) {
            System.out.println("The puzzle is inconsistent.");
            return false;
        }
        if (size > MAX_SIZE) {
            System.out.println("Puzzle size is too big (>" + MAX_SIZE + ").");
            return false;
        }
        if (!isLineUpperCase(line)) {
            System.out.println("The puzzle has lower case characters.");
            return false;
        }
        return true;
    }

    private static void printPuzzle(char[][] puzzle) {
        for (char[] line : puzzle) {
            System.out.println(line);
        }
    }
}
