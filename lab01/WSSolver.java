package lab01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class WSSolver {
    private static final int MAX_SIZE = 40;
    static WordList words = new WordList();

    public static void main(String[] args) throws FileNotFoundException {
        char[][] puzzle = new char[0][0];
        String line;
        int lnCount = 0;
        int size = 0;

        PrintWriter pw = new PrintWriter("out1");

        if (args.length == 0) {
            System.err.println("ERROR: Missing one argument with file of word solver");
            System.exit(1);
        }

        Scanner reader = new Scanner(new FileReader(args[0]));

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

                try {
                    words.addAll(Arrays.stream(tempWords).map(Word::new).toList());
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                    System.exit(1);
                }
            }

        }

        WordSearch original = new WordSearch(puzzle);
        words.forEach(original::findWord);

        WordSearch solved = new WordSearch(size);
        for (Word word : words) {
            solved.addWord(word.getWord(), word.getRow(), word.getCol(), word.getDirection());
        }

        words.printWordList(pw, true);
        solved.printPuzzle(pw, true);

        pw.close();
        reader.close();
    }

    /**
     * <b> Check if all lines are valid</b>
     *
     * <p>
     * The requirements of Word Search is to have all letters in UpperCase and be a square (40x40 max).
     * If there is numbers return false.
     * </p>
     *
     * @param line    Line to check.
     * @param size    Puzzle size
     * @param lnCount Number of lines expected.
     * @return Whether the word search is valid or not.
     */
    private static boolean isLineValid(String line, int size, int lnCount) {
        if (lnCount > size || size != line.length()) {
            System.err.println("The puzzle is inconsistent.");
            return false;
        }
        if (size > MAX_SIZE) {
            System.err.println("Puzzle size is too big (>" + MAX_SIZE + ").");
            return false;
        }
        if (!Utils.isLineUpperCase(line)) {
            System.err.println("The puzzle has lower case characters.");
            return false;
        }

        for (char c : line.toCharArray()) {
            if (!Character.isAlphabetic(c)) {
                System.err.println("The puzzle has unsupported characters.");
                return false;
            }
        }

        return true;
    }
}