package lab01;

import java.io.*;
import java.util.*;

public class WSGenerator {

    private static char[][] puzzle;
    private static String[][] registration;
    private static final ArrayList<String> words = new ArrayList<>();
    private static final ArrayList<String> wordLines = new ArrayList<>();
    private static int wordID = 0;
    private static final String WORD_SEPARATORS = "[, ;]";
    private static final int MAX_PUZZLE_SIZE = 40;

    public static void main(String[] args) {
        String inputFile = "";
        String outputFile = "";
        int size = 0;

        if (args.length == 4 || args.length == 6) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-i"))
                    inputFile = args[i + 1];
                if (args[i].equals("-o"))
                    outputFile = args[i + 1];
                if (args[i].equals("-s"))
                    size = Integer.parseInt(args[i + 1]);
            }
        } else {
            System.out.println("Invalid command line arguments");
            System.exit(0);
        }

        if (!readData(inputFile)) {
            System.out.println("Invalid input file");
            System.exit(0);
        }

        if (size <= 0 || size > MAX_PUZZLE_SIZE) {
            System.out.println("Invalid puzzle size");
            System.exit(0);
        }

        initializePuzzle(size);
        insertWords(size);
        fillEmptyCells(size);

        if (!outputFile.isEmpty())
            writeToFile(outputFile);
        else
            printPuzzleAndWords();
    }

    private static boolean readData(String inputFile) {
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNext()) {
                String line = scanner.next();
                String[] separatedWords = line.split(WORD_SEPARATORS);
                words.addAll(Arrays.asList(separatedWords));
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    private static void initializePuzzle(int size) {
        puzzle = new char[size][size];
        registration = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                puzzle[i][j] = '.';
                registration[i][j] = "";
            }
        }
    }

    private static void insertWords(int size) {
        Random random = new Random();

        for (String word : words) {
            wordID++;
            word = word.toUpperCase();

            boolean inserted = false;

            while (!inserted) {
                int row = random.nextInt(size);
                int col = random.nextInt(size);
                Direction randomDirection = Direction.getRandomDirection();

                boolean canInsert = false;

                switch (randomDirection) {
                    case RIGHT:
                        canInsert = insertWordHorizontal(word, row, col, size);
                        break;
                    case DOWN:
                        canInsert = insertWordVertical(word, row, col, size);
                        break;
                    case UP:
                        canInsert = insertWordVerticalReverse(word, row, col, size);
                        break;
                    case LEFT:
                        canInsert = insertWordHorizontalReverse(word, row, col);
                        break;
                    case DIAG_UP_LEFT:
                        canInsert = insertWordDiagonalUpLeft(word, row, col);
                        break;
                    case DIAG_UP_RIGHT:
                        canInsert = insertWordDiagonalUpRight(word, row, col, size);
                        break;
                    case DIAG_DOWN_LEFT:
                        canInsert = insertWordDiagonalDownLeft(word, row, col, size);
                        break;
                    case DIAG_DOWN_RIGHT:
                        canInsert = insertWordDiagonalDownRight(word, row, col, size);
                        break;
                }

                if (canInsert) {
                    inserted = true;
                }
            }
        }
    }

    private static boolean insertWordHorizontal(String word, int row, int col, int size) {
        if (col + word.length() > size) return false; // Verifica se a palavra cabe na linha

        for (int i =  0; i < word.length(); i++) {
            if (puzzle[row][col + i] != '.' && puzzle[row][col + i] != word.charAt(i)) {
                return false;
            }
        }

        for (int i =  0; i < word.length(); i++) {
            puzzle[row][col + i] = word.charAt(i);
            registration[row][col + i] = String.valueOf(wordID);
        }

        return true;
    }


    private static boolean insertWordVertical(String word, int row, int col, int size) {
        if (row + word.length() > size) return false;

        for (int i =  0; i < word.length(); i++) {
            if (puzzle[row + i][col] != '.' && puzzle[row + i][col] != word.charAt(i)) {
                return false;
            }
        }

        for (int i =  0; i < word.length(); i++) {
            puzzle[row + i][col] = word.charAt(i);
            registration[row + i][col] = String.valueOf(wordID);
        }

        return true;
    }


    private static boolean insertWordVerticalReverse(String word, int row, int col, int size) {
        if (row - word.length() +  1 <  0) return false;

        for (int i =  0; i < word.length(); i++) {
            if (puzzle[row - i][col] != '.' && puzzle[row - i][col] != word.charAt(i)) {
                return false;
            }
        }

        for (int i =  0; i < word.length(); i++) {
            puzzle[row - i][col] = word.charAt(i);
            registration[row - i][col] = String.valueOf(wordID);
        }

        return true;
    }


    private static boolean insertWordHorizontalReverse(String word, int row, int col) {
        if (col - word.length() +  1 <  0) return false;

        for (int i =  0; i < word.length(); i++) {
            if (puzzle[row][col - i] != '.' && puzzle[row][col - i] != word.charAt(i)) {
                return false;
            }
        }

        for (int i =  0; i < word.length(); i++) {
            puzzle[row][col - i] = word.charAt(i);
            registration[row][col - i] = String.valueOf(wordID);
        }

        return true;
    }


    private static boolean insertWordDiagonalUpLeft(String word, int row, int col) {
        if (row - word.length() +  1 <  0 || col - word.length() +  1 <  0) return false;

        for (int i =  0; i < word.length(); i++) {
            if (puzzle[row - i][col - i] != '.' && puzzle[row - i][col - i] != word.charAt(i)) {
                return false;
            }
        }

        for (int i =  0; i < word.length(); i++) {
            puzzle[row - i][col - i] = word.charAt(i);
            registration[row - i][col - i] = String.valueOf(wordID);
        }

        return true;
    }


    private static boolean insertWordDiagonalUpRight(String word, int row, int col, int size) {
        if (row - word.length() +  1 <  0 || col + word.length() -  1 >= size) return false;

        for (int i =  0; i < word.length(); i++) {
            if (puzzle[row - i][col + i] != '.' && puzzle[row - i][col + i] != word.charAt(i)) {
                return false;
            }
        }

        for (int i =  0; i < word.length(); i++) {
            puzzle[row - i][col + i] = word.charAt(i);
            registration[row - i][col + i] = String.valueOf(wordID);
        }

        return true;
    }


    private static boolean insertWordDiagonalDownLeft(String word, int row, int col, int size) {
        if (row + word.length() -  1 >= size || col - word.length() +  1 <  0) return false;

        for (int i =  0; i < word.length(); i++) {
            if (puzzle[row + i][col - i] != '.' && puzzle[row + i][col - i] != word.charAt(i)) {
                return false;
            }
        }

        for (int i =  0; i < word.length(); i++) {
            puzzle[row + i][col - i] = word.charAt(i);
            registration[row + i][col - i] = String.valueOf(wordID);
        }

        return true;
    }


    private static boolean insertWordDiagonalDownRight(String word, int row, int col, int size) {
        if (row + word.length() -  1 >= size || col + word.length() -  1 >= size) return false;

        for (int i =  0; i < word.length(); i++) {
            if (puzzle[row + i][col + i] != '.' && puzzle[row + i][col + i] != word.charAt(i)) {
                return false;
            }
        }

        for (int i =  0; i < word.length(); i++) {
            puzzle[row + i][col + i] = word.charAt(i);
            registration[row + i][col + i] = String.valueOf(wordID);
        }

        return true;
    }


    private static void fillEmptyCells(int size) {
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (puzzle[i][j] == '.') {
                    puzzle[i][j] = (char) ('A' + random.nextInt(26));
                }
            }
        }
    }

    private static void writeToFile(String outputFile) {
        try (PrintWriter saveFile = new PrintWriter(new FileWriter(outputFile))) {
            for (char[] row : puzzle) {
                for (char c : row) {
                    saveFile.print(c);
                }
                saveFile.println();
            }
            for (String word : words) {
                saveFile.printf("%s,", word);
            }
        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printPuzzleAndWords() {
        for (char[] row : puzzle) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        for (String line : wordLines) {
            System.out.println(line);
        }
    }

    private enum Direction {
        RIGHT, DOWN, UP, LEFT, DIAG_UP_LEFT, DIAG_UP_RIGHT, DIAG_DOWN_LEFT, DIAG_DOWN_RIGHT;

        private static final List<Direction> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Direction getRandomDirection() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
}
