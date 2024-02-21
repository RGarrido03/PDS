package lab01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WSGenerator {
    private static final List<String> words = new ArrayList<>();

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
                words.addAll(
                        Arrays.asList(scanner.nextLine().trim().split("[ ,;]"))
                );
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.exit(1);
        }

        createWS(size, output);
    }

    private static void createWS(int size, String filename) {
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
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
        boolean basicValidation = args[0].equals("-i") && args[1].endsWith(".txt") && args[2].equals("-s") && isNumeric(
                args[3]);
        return switch (args.length) {
            case 4 -> basicValidation;
            case 6 -> basicValidation && args[4].equals("-o") && args[5].endsWith(".txt");
            default -> false;
        };
    }
}
