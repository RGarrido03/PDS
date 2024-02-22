package lab01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class WSGenerator {
    private static final List<Word> words = new ArrayList<>();

    public static void main(String[] args) {
        if (!areArgsValid(args)) {
            System.err.println("Invalid number of arguments.");
            System.exit(1);
        }

        String input = args[1];
        String output = args.length == 6 ? args[5] : "wordlist_result.txt";
        int size = Integer.parseInt(args[3]);

        try {
            Scanner scanner = new Scanner(new FileReader(input));

            while (scanner.hasNextLine()) {
                Arrays.stream(scanner.nextLine().trim().split("[ ,;]"))
                      .forEach(word -> words.add(new Word(word)));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.exit(1);
        }

        try {
            createWS(size, output);
        } catch (FileNotFoundException e) {
            System.err.println("File " + output + " isn't found or cannot be created.");
        }
    }

    private static void createWS(int size, String filename) throws FileNotFoundException {
        char[][] puzzle = Utils.initializePuzzle(size);

        for (Word word : words) {
            if (word.getLength() > size) {
                System.err.println("Word " + word + " is too long.");
                System.exit(1);
            }
            generatePosition(word, puzzle);
        }

        try (PrintWriter writer = new PrintWriter(filename)) {
            for (char[] line : puzzle) {
                Utils.printToConsoleAndFile(Arrays.toString(line), writer);
            }
        }
    }

    private static void generatePosition(Word word, char[][] puzzle) {
        Direction direction = Utils.randomDirection();
        int[] rowBoundaries = getRowBoundaries(puzzle, direction, word.getLength());
        int[] colBoundaries = getColBoundaries(puzzle, direction, word.getLength());

        int row = ThreadLocalRandom.current().nextInt(rowBoundaries[0], rowBoundaries[1] + 1);
        int col = ThreadLocalRandom.current().nextInt(colBoundaries[0], colBoundaries[1] + 1);

        word.setRow(row);
        word.setCol(col);
        word.setDirection(direction);
    }

    private static int[] getRowBoundaries(char[][] puzzle, Direction direction, int length) {
        int difference = puzzle.length - length;
        int end = puzzle.length - 1;

        return switch (direction) {
            case up, upLeft, upRight -> new int[]{end - difference, end};
            case down, downLeft, downRight -> new int[]{0, difference};
            case left, right -> new int[]{0, end};
        };
    }

    private static int[] getColBoundaries(char[][] puzzle, Direction direction, int length) {
        int difference = puzzle.length - length;
        int end = puzzle.length - 1;

        return switch (direction) {
            case up, down -> new int[]{0, end};
            case left, upLeft, downLeft -> new int[]{end - difference, end};
            case right, upRight, downRight -> new int[]{0, difference};
        };
    }

    /**
     * <b>Validate arguments.</b>
     * <p>
     * If the length of the array is 4, it checks the input and the size of the word search.
     * If it's 6, then it performs the same four checks, but also if the output file is valid.
     *
     * @param args Program arguments
     * @return Whether arguments are valid or not
     */
    private static boolean areArgsValid(String[] args) {
        if (args.length < 4) {
            return false;
        }

        boolean basicValidation =
                args[0].equals("-i") && args[1].endsWith(".txt") && args[2].equals("-s") && Utils.isNumeric(
                        args[3]);
        return switch (args.length) {
            case 4 -> basicValidation;
            case 6 -> basicValidation && args[4].equals("-o") && args[5].endsWith(".txt");
            default -> false;
        };
    }
}
