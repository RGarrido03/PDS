package lab01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SopaDeLetras {
    private static final int MAX_SIZE = 40;

    public static void main(String[] args) throws FileNotFoundException {
        char[][] puzzle;
        String[] targetWords;
        String line;
        int size = 0;

        Scanner reader = new Scanner(new FileReader("sopaDeLetras.txt"));

        while (reader.hasNextLine()) {
            line = reader.nextLine().trim();

            // Puzzle
            if (Character.isUpperCase(line.charAt(0))) {
                if (size == 0) {
                    size = line.length();
                } else if (size != line.length()) {
                    System.out.println("The puzzle is inconsistent. Exiting...");
                    return;
                }

                if (size > MAX_SIZE) {
                    System.out.println("Puzzle size is too big (>40). Exiting...");
                    return;
                }
                if (!isLineUpperCase(line)) {
                    System.out.println("puzzle has lower case characters. Exiting...");
                    return;
                }

                /* puzzle = new char[line.length()][line.length()];

                for (int i = 0; i < line.length(); i++) {
                    if (puzzle.length == puzzle[0].length) {
                        // insert in bidimensional array
                        puzzle[i] = line.toCharArray();
                        System.out.println(puzzle);
                        // read next line
                        line = reader.nextLine();
                    } else {
                        System.out.println("Sopa de Letras inválida!");
                    }
                } */
            } else {
                System.out.println("Words");
            }
        }
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
}
