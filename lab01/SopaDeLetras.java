package lab01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SopaDeLetras {
    public static void main(String[] args) {
        char[][] puzzle;
        String[] targetWords;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("lab01/sopaDeLetras.txt"));
            String line = reader.readLine();

            while (isUpperCase(line)) {
                puzzle = new char[line.length()][line.length()];

                for (int i = 0; i < line.length(); i++) {

                    if (puzzle.length == puzzle[0].length) {
                        // insert in bidimensional array
                        puzzle[i] = line.toCharArray();
                        System.out.println(puzzle);
                        // read next line
                        line = reader.readLine();
                    } else {
                        System.out.println("Sopa de Letras inválida!");
                    }
                }
            }

            while (!isUpperCase(line)) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static boolean isUpperCase(String line) {
        for (char c : line.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

}
