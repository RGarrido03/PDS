import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class WSSolver {

    private static void problemOrganizer(String filePath, ArrayList<String> puzzleLines, ArrayList<String> wordLines) throws Exception {
        ArrayList<String> problemLines = new ArrayList<String>();

        Scanner scanner = new Scanner(new FileReader(filePath));
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            problemLines.add(line);
        }
        scanner.close();

        int puzzleWidth = problemLines.get(0).length();

        for (int i = 0; i < problemLines.size(); i++) {
            if (i < puzzleWidth) {
                puzzleLines.add(problemLines.get(i));
                continue;
            }

            String[] parts = problemLines.get(i).split("[,; ]");
            for (String part : parts) {
                wordLines.add(part);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java WSSolver <file-name>");
            return;
        }

        ArrayList<String> puzzleLines = new ArrayList<String>();
        ArrayList<String> wordLines = new ArrayList<String>();

        problemOrganizer(args[0], puzzleLines, wordLines);

        Puzzle puzzle = new Puzzle(puzzleLines.get(0).length(), puzzleLines.size());
        puzzle.setSoup(puzzleLines);
        puzzle.setWords(wordLines);

        boolean isValid = puzzle.isInputValid(puzzleLines);
        if (!isValid) {
            System.err.println("[Error] Invalid puzzle");
            return;
        }

        puzzle.solve();

        for (Word word : puzzle.getWords()) {
            if (word.isFound()) continue;

            System.err.println("[Error] Invalid puzzle");
            return;
        }
        System.out.println(puzzle.solutionToString());
    }
}
