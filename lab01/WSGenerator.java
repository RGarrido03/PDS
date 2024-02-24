package lab01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WSGenerator {
    private static final List<Word> words = new ArrayList<>();

    public static void main(String[] args) {
        if (!areArgsValid(args)) {
            System.err.println("Invalid number of arguments.");
            System.exit(1);
        }

        String input = args[1];
        String output = args.length == 6 ? args[5] : null;
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

        WordSearch wordSearch = createWS(size);
        if (output != null) {
            try (PrintWriter writer = new PrintWriter(output)) {
                wordSearch.printPuzzle(writer);
                List<String> wordsList = words.stream().map(Word::getWord).toList();
                System.out.println(wordsList);
                writer.println(wordsList);
            } catch (FileNotFoundException e) {
                System.err.println("File " + output + " isn't found or cannot be created.");
                System.exit(1);
            }
            return;
        }
        wordSearch.printPuzzle();
        System.out.println(words.stream().map(Word::getWord).toList());
    }

    private static WordSearch createWS(int size) {
        WordSearch puzzle = new WordSearch(size);

        for (Word word : words) {
            if (word.getLength() > size) {
                System.err.println("Word " + word + " is too long.");
                System.exit(1);
            }
            puzzle.generatePosition(word);
            puzzle.addWord(word.getWord(), word.getRow(), word.getCol(), word.getDirection());
        }

        return puzzle;
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
