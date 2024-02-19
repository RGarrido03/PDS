package lab01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SopaDeLetras {
    private static final int MAX_SIZE = 40;

    public static void main(String[] args) throws FileNotFoundException {
        char[][] puzzle = new char[0][0];
        List<String> words = new ArrayList<>();
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
                String[] tempWords = line.split("[ ,;]");
                words.addAll(Arrays.asList(tempWords));
            }
        }

        printPuzzle(puzzle);
        System.out.println(words);
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

        for (char c : line.toCharArray()){
            if (Character.isDigit(c)){
                return false;
            }
        }

        return true;
    }

    private static void printPuzzle(char[][] puzzle) {
        for (char[] line : puzzle) {
            System.out.println(line);
        }
    }
}
