package lab01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SopaDeLetras {
    public static void main(String[] args) throws FileNotFoundException {
        char[][] puzzle;
        String[] targetWords;

        Scanner reader = new Scanner(new FileReader("sopaDeLetras.txt"));
        String line = reader.nextLine();

        while (isLineUpperCase(line)) {
            puzzle = new char[line.length()][line.length()];

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
            }
        }

        while (!isLineUpperCase(line)) {
            System.out.println(line);
        }
        reader.close();

    }

    static boolean isLineUpperCase(String line) {
        for (char c : line.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

}
