import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WSGenerator {

	public static ArrayList<String> readWordList(String wordList, ArrayList<String> wordListLines) throws Exception {
		ArrayList<String> words = new ArrayList<String>();
		Scanner scanner = new Scanner(new FileReader(wordList));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split("[,; ]");
			for (String word : parts) {
				words.add(word);
			}

			wordListLines.add(line);
		}
		scanner.close();
		return words;
	}

    public static void main(String[] args) throws Exception {
        int soupSize = 0;
        String wordList = "";
        String outputFile = "";

		ArrayList<String> wordListLines = new ArrayList<String>();

        if (args.length < 4 || args.length > 6) {
            System.out.println("Usage: java WSGenerator -i <word-list> -s <size> -o <output-file>");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i")) {
                wordList = args[i + 1];
            } else if (args[i].equals("-s")) {
                soupSize = Integer.parseInt(args[i + 1]);
            } else if (args[i].equals("-o")) {
                outputFile = args[i + 1];
            }
        }

		ArrayList<String> words = readWordList(wordList, wordListLines);

		for (String word : words) {
			if (word.length() > soupSize) {
				System.out.println("Error: word " + word + " is too long for the soup size (" + soupSize + ").");
				return;
			}
		}

		Puzzle puzzle = new Puzzle(soupSize, soupSize);
		puzzle.setWords(words);
		puzzle.generateFromWords();

		StringBuilder sb = new StringBuilder();
		sb.append(puzzle.toString());
		sb.append("\n");
		for (String word : wordListLines) {
			sb.append(word);
			if (wordListLines.indexOf(word) != wordListLines.size() - 1) {
				sb.append("\n");
			}
		}
        
        if (outputFile.equals("")) {
            System.out.println(sb.toString());
        } else {
            PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
			writer.print(sb.toString());
			writer.close();
        }
    }
}
