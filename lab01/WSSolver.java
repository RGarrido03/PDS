package lab01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WSSolver {
    private static final int MAX_SIZE = 40;
    static List<Word> words = new ArrayList<>();

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
                words.addAll(Arrays.stream(tempWords).map(Word::new).toList());
            }

        }

        List<String> wordStrList = words.stream().map(Word::getWord).toList();

        WordSearch original = new WordSearch(puzzle);
        System.out.println(wordStrList);
        solvePuzzle(original, words);

        WordSearch wordSearch = new WordSearch(size);

        for (Word word : words) {
            wordSearch.addWord(word.getWord(), word.getRow(), word.getCol(), word.getDirection());
        }

        wordSearch.printPuzzle(pw);
        pw.close();
        reader.close();
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
        if (!Utils.isLineUpperCase(line)) {
            System.out.println("The puzzle has lower case characters.");
            return false;
        }

        for (char c : line.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    private static void solvePuzzle(WordSearch puzzle, List<Word> words) {
        words.forEach(word -> findWord(puzzle, word));
    }

    private static void findWord(WordSearch puzzle, Word word) {
        char[] letters = word.getWord().toUpperCase().toCharArray();
        List<int[]> positions = new ArrayList<>();
        for (int i = 0; i < puzzle.getSize(); i++) {
            String allLettersInLine = new String(puzzle.getLine(i));

            int index = allLettersInLine.indexOf(letters[0]);
            while (index >= 0) {
                int[] coordinate = new int[]{i, index};

                positions.add(coordinate);
                index = allLettersInLine.indexOf(letters[0], index + 1);
            }
        }

        for (int[] coordinate : positions) {
            Direction resultDirection = testCoordinate(puzzle, word.getWord(), coordinate);

            if (resultDirection != null) {
                word.setRow(coordinate[0]);
                word.setCol(coordinate[1]);
                word.setDirection(resultDirection);

                System.out.printf("%-15s%-10d%d%s%-10d%-15s\n", word.getWord(), word.getLength(), word.getRow(), ",",
                                  word.getCol(), Utils.capitalizeString(word.getDirection().toString()));
                break;
            }
        }
    }

    private static Direction testCoordinate(WordSearch puzzle, String word, int[] coordinate) {
        char[] letters = word.toUpperCase().toCharArray();

        for (Direction direction : Direction.values()) {
            if (testNextCoordinate(puzzle, letters, coordinate, direction)) {
                return direction;
            }
        }
        return null;
    }

    private static boolean testNextCoordinate(WordSearch puzzle, char[] letters, int[] coord, Direction direction) {
        int[] nextCoordinate = switch (direction) {
            case up -> new int[]{coord[0] - 1, coord[1]};
            case down -> new int[]{coord[0] + 1, coord[1]};
            case left -> new int[]{coord[0], coord[1] - 1};
            case right -> new int[]{coord[0], coord[1] + 1};
            case upLeft -> new int[]{coord[0] - 1, coord[1] - 1};
            case upRight -> new int[]{coord[0] - 1, coord[1] + 1};
            case downLeft -> new int[]{coord[0] + 1, coord[1] - 1};
            case downRight -> new int[]{coord[0] + 1, coord[1] + 1};
        };

        if (validCoordinate(puzzle, nextCoordinate) && verifyLetter(puzzle, nextCoordinate, letters[1])) {
            if (letters.length == 2) {
                return true;
            } else {
                return testNextCoordinate(puzzle, Arrays.copyOfRange(letters, 1, letters.length), nextCoordinate,
                                          direction);
            }
        } else {
            return false;
        }
    }

    private static boolean validCoordinate(WordSearch puzzle, int[] coordinate) {
        return coordinate[0] >= 0 && coordinate[1] >= 0 && coordinate[0] < puzzle.getSize() && coordinate[1] < puzzle.getSize();
    }

    private static boolean verifyLetter(WordSearch puzzle, int[] coordinate, char charToVerify) {
        return puzzle.getChar(coordinate[0], coordinate[1]) == Character.toUpperCase(charToVerify);
    }
}