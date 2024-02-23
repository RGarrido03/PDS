package lab01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SopaDeLetras {
    private static final int MAX_SIZE = 40;
    static List<Word> words = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        char[][] puzzle = new char[0][0];
        String line;
        int lnCount = 0;
        int size = 0;

        /*if (args.length < 1){
            System.out.println("ERROR: Missing one argument with file of word solver");
            System.exit(1);
        }*/
        System.out.println("Begin");

        Scanner reader = new Scanner(new FileReader("sopaDeLetras.txt"));

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

        printPuzzle(puzzle, wordStrList);
        System.out.println(wordStrList);
        solvePuzzle(puzzle, words);

        WordSearch wordSearch = new WordSearch(size);

        for(Word word: words){
            wordSearch.addWord(word.getWord(), word.getRow(),word.getCol(), word.getDirection());
        }

        wordSearch.printPuzzle();

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

    private static boolean isLineValid(String line, int size, int lnCount) {
        if (lnCount > size || size != line.length()) {
            System.out.println("The puzzle is inconsistent.");
            return false;
        }
        if (size > MAX_SIZE) {
            System.out.println("Puzzle size is too big (>" + MAX_SIZE + ").");
            return false;
        }
        if (!isLineUpperCase(line)) {
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

    private static void printPuzzle(char[][] puzzle, List<String> words) {
        for (char[] line : puzzle) {
            System.out.println(line);
        }

//        for (int i = 0; i < puzzle.length; i++) {
//            for (int j = 0; j < puzzle[0].length; j++) {
//
//                int row = i;
//                int col = j;
//
//                //Check if word contains in puzzle
//                boolean characterMatch = words.stream().anyMatch(word -> word.contains(Character.toString(puzzle[row][col]).toLowerCase()));
//
//
//                if (characterMatch) {
//                    System.out.print(puzzle[row][col]);
//                } else {
//                    System.out.print(".");
//                }
//
//            }
//            System.out.println();
//        }
    }

    private static void solvePuzzle(char[][] puzzle, List<Word> words) {
        for (Word word : words) {
            findWord(puzzle, word);
        }
    }

    private static void findWord(char[][] puzzle, Word word) {
        char[] letters = word.getWord().toUpperCase().toCharArray();
        List<int[]> positions = new ArrayList<>();
        for (int i = 0; i < puzzle.length; i++) {
            String allLettersInLine = new String(puzzle[i]);

            int index = allLettersInLine.indexOf(letters[0]);
            while (index >= 0) {
                int[] coordinate = new int[]{i, index};

                positions.add(coordinate);
                index = allLettersInLine.indexOf(letters[0], index + 1);
            }
        }

        for (int[] coordinate : positions) {
            String result = testCoordinate(puzzle, word.getWord(), coordinate);

            if(result != null){

                word.setRow(coordinate[0]);
                word.setCol(coordinate[1]);
                word.setDirection(Direction.valueOf(result));

                System.out.printf("%-15s%-10d%d%s%-10d%-15s\n", word.getWord(), word.getLength(), word.getRow(),",", word.getCol(), result);
                break;
            }
        }


    }

    private static String testCoordinate(char[][] puzzle, String word, int[] coordinate) {
        char[] letters = word.toUpperCase().toCharArray();
        if (testNextCoordinate(puzzle, letters, coordinate, Direction.up)) {
            return "up";
        } else if (testNextCoordinate(puzzle, letters, coordinate, Direction.down)) {
            return "down";
        } else if (testNextCoordinate(puzzle, letters, coordinate, Direction.left)) {
            return "left";
        } else if (testNextCoordinate(puzzle, letters, coordinate, Direction.right)) {
            return "right";
        } else if (testNextCoordinate(puzzle, letters, coordinate, Direction.upLeft)) {
            return "upLeft";
        } else if (testNextCoordinate(puzzle, letters, coordinate, Direction.upRight)) {
            return "upRight";
        } else if (testNextCoordinate(puzzle, letters, coordinate, Direction.downLeft)) {
            return "downLeft";
        } else if (testNextCoordinate(puzzle, letters, coordinate, Direction.downRight)) {
            return "downRight";
        }
        else {
            return null;
        }
    }

    private static boolean testNextCoordinate(char[][] puzzle, char[] letters, int[] coord, Direction direction) {
        int[] nextCoordinate = null;
        switch (direction) {
            case up:
                nextCoordinate = new int[]{coord[0] - 1, coord[1]};
                break;
            case down:
                nextCoordinate = new int[]{coord[0] + 1, coord[1]};
                break;
            case left:
                nextCoordinate = new int[]{coord[0], coord[1] - 1};
                break;
            case right:
                nextCoordinate = new int[]{coord[0], coord[1] + 1};
                break;
            case upLeft:
                nextCoordinate = new int[]{coord[0] - 1, coord[1] - 1};
                break;
            case upRight:
                nextCoordinate = new int[]{coord[0] - 1, coord[1] + 1};
                break;
            case downLeft:
                nextCoordinate = new int[]{coord[0] + 1, coord[1] - 1};
                break;
            case downRight:
                nextCoordinate = new int[]{coord[0] + 1, coord[1] + 1};
                break;
        }

        if (validCoordinate(puzzle, nextCoordinate) && verifyLetter(puzzle, nextCoordinate, letters[1])) {
            if(letters.length==2) {
                return true;
            }
            else {
                return testNextCoordinate(puzzle, Arrays.copyOfRange(letters, 1, letters.length), nextCoordinate, direction);
            }
        } else {
            return false;
        }
    }

    private static boolean validCoordinate(char[][] puzzle, int[] coordinate) {
        return coordinate[0] >= 0 && coordinate[1] >= 0 && coordinate[0] < puzzle.length && coordinate[1] < puzzle[0].length;
    }

    private static boolean verifyLetter(char[][] puzzle, int[] coordinate, char charToVerify) {
        return puzzle[coordinate[0]][coordinate[1]] == Character.toUpperCase(charToVerify);
    }
}