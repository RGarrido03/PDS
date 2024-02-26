package lab01;

import java.io.*;
import java.util.*;

public class WSolver {

    private static final String ERROR_INVALID_FORMAT = "ERRO: Formato inválido no arquivo de palavras";
    private static final String ERROR_EMPTY_LINE = "ERRO: Linha vazia detectada no arquivo";
    private static final String PUZZLE_NOT_SQUARE = "ERRO: O puzzle não é quadrado";
    private static final String ERROR_FILE_NOT_FOUND = "ERRO: Arquivo não encontrado";
    private static final String ERROR_SOUP_SIZE = "ERRO: Tamanho da Sopa é muito grande (>40)";

    private static final String WORD_SEPARATORS = "[, ;]";

    private char[][] puzzle;
    private int puzzleSize;

    public enum Direction {
        CIMA, BAIXO, ESQUERDA, DIREITA, DIAGONAL_CIMA_ESQUERDA, DIAGONAL_CIMA_DIREITA, DIAGONAL_BAIXO_ESQUERDA,
        DIAGONAL_BAIXO_DIREITA, NENHUM;

        private static final List<Direction> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Direction getRandomDirection() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }

        public int getRowDelta() {
            return switch (this) {
                case CIMA -> -1;
                case BAIXO -> 1;
                default -> 0;
            };
        }

        public int getColDelta() {
            return switch (this) {
                case ESQUERDA -> -1;
                case DIREITA -> 1;
                default -> 0;
            };
        }
    }

    public static class WordPosition {
        String word;
        int startRow;
        int startCol;
        Direction direction;

        WordPosition(String word, int startRow, int startCol, Direction direction) {
            this.word = word;
            this.startRow = startRow;
            this.startCol = startCol;
            this.direction = direction;
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java WSolver <filename>");
            System.exit(1);
        }

        WSolver wsSolver = new WSolver();
        wsSolver.run(args[0]);
    }

    public void run(String filename) {
        try {
            puzzle = readSoupFromFile(filename);
            puzzleSize = puzzle.length;

            if (puzzleSize > 40) {
                System.out.println(ERROR_SOUP_SIZE);
                System.exit(1);
            }

            ArrayList<String> words = wordsToFind(filename, puzzleSize);
            ArrayList<WordPosition> wordPositions = new ArrayList<>();
            solvePuzzle(words, wordPositions);

            printWordTable(wordPositions);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private char[][] readSoupFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    throw new IOException(ERROR_EMPTY_LINE);
                }

                lines.add(line);
            }

            puzzleSize = lines.size();

            if (lines.stream().anyMatch(l -> l.length() != puzzleSize)) {
                throw new IOException(PUZZLE_NOT_SQUARE);
            }

            char[][] soup = new char[puzzleSize][puzzleSize];
            for (int i = 0; i < puzzleSize; i++) {
                String[] chars = lines.get(i).split("");
                for (int j = 0; j < puzzleSize; j++) {
                    soup[i][j] = chars[j].charAt(0);
                }
            }
            return soup;
        }
    }

    private ArrayList<String> wordsToFind(String fileName, int size) throws IOException {
        ArrayList<String> words = new ArrayList<>();
        Set<String> processedWords = new HashSet<>();

        try (Scanner fileScan = new Scanner(new File(fileName))) {
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine().trim();
                if (line.isEmpty()) {
                    throw new IOException(ERROR_EMPTY_LINE);
                }

                if (!line.matches("[A-Za-z]+")) {
                    throw new IOException(ERROR_INVALID_FORMAT);
                }

                String[] lineWords = line.split(WORD_SEPARATORS);
                for (String lineWord : lineWords) {
                    String word = lineWord.trim();
                    if (!isAlphabetic(word)) {
                        System.out.printf("Aviso: A palavra '%s' não faz parte do alfabeto%n", word);
                        continue;
                    }
                    if (word.length() < 3 || word.length() > size) {
                        System.out.printf("Aviso: Palavra %s não tem o tamanho válido%n", word);
                        continue;
                    }
                    if (isSubsetOfLargerWord(word, processedWords)) {
                        continue;
                    }
                    words.add(word);
                    processedWords.add(word.toUpperCase());
                }
            }
        } catch (FileNotFoundException e) {
            throw new IOException(ERROR_FILE_NOT_FOUND, e);
        }

        return words;
    }

    private boolean isSubsetOfLargerWord(String word, Set<String> processedWords) {
        for (String processedWord : processedWords) {
            if (processedWord.contains(word.toUpperCase())) {
                System.out.printf("Aviso: Ignorando palavra '%s' pois é um subconjunto de uma palavra maior%n", word);
                return true;
            }
        }
        return false;
    }

    private boolean isAlphabetic(String word) {
        return word.chars().allMatch(Character::isAlphabetic);
    }

    private void solvePuzzle(ArrayList<String> words, ArrayList<WordPosition> wordPositions) {
        for (String word : words) {
            Direction direction = Direction.getRandomDirection();
            WordPosition wordPos = new WordPosition(word, 0, 0, direction);
            boolean found = findWord(puzzle, wordPos);
            if (found) {
                System.out.println(wordPos.word + ": Encontrada em (" + wordPos.startRow + "," + wordPos.startCol + ") na direção " + wordPos.direction);
                wordPositions.add(wordPos);
            } else {
                System.out.println(wordPos.word + ": Não encontrada");
            }
        }
    }

    private void markLetters(char[][] soup, String word, int row, int col, Direction direction) {
        int length = word.length();
        int deltaRow = direction.getRowDelta();
        int deltaCol = direction.getColDelta();

        for (int i = 0; i < length; i++) {
            if (soup[row][col] == '.') {
                System.out.println("Aviso: Tentativa de marcar a letra ('" + word.charAt(i) + "') da palavra '" + word + "' duas vezes.");
            } else {
                soup[row][col] = '.';
            }
            row += deltaRow;
            col += deltaCol;
        }
    }

    private void printWordTable(ArrayList<WordPosition> wordPositions) {
        System.out.println("Tabela de Palavras:");
        for (WordPosition wordPos : wordPositions) {
            System.out.printf("%d | (%d, %d) | %s%n", wordPos.word.length(), wordPos.startRow, wordPos.startCol, wordPos.direction);
        }
    }

    private void printSolution(char[][] soup, WordPosition wordPos) {
        int size = soup.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isPartOfWord(wordPos, i, j)) {
                    System.out.print(Character.toLowerCase(soup[i][j]));
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean findWord(char[][] soup, WordPosition wordPos) {
        for (Direction direction : Direction.values()) {
            boolean found = false;

            switch (direction) {
                case DIREITA:
                    found = findWordHorizontal(soup, wordPos);
                    break;
                case BAIXO:
                    found = findWordVertical(soup, wordPos);
                    break;
                case CIMA:
                    found = findWordVerticalReverse(soup, wordPos);
                    break;
                case ESQUERDA:
                    found = findWordHorizontalReverse(soup, wordPos);
                    break;
                case DIAGONAL_CIMA_ESQUERDA:
                    found = findWordDiagonalUpLeft(soup, wordPos);
                    break;
                case DIAGONAL_CIMA_DIREITA:
                    found = findWordDiagonalUpRight(soup, wordPos);
                    break;
                case DIAGONAL_BAIXO_ESQUERDA:
                    found = findWordDiagonalDownLeft(soup, wordPos);
                    break;
                case DIAGONAL_BAIXO_DIREITA:
                    found = findWordDiagonalDownRight(soup, wordPos);
                    break;
                case NENHUM:
                    break;
            }

            if (found) {
                System.out.println("Encontrada em (" + wordPos.startRow + "," + wordPos.startCol + "): " + direction);
                return true;
            }
        }

        return false;
    }

    private boolean findWordHorizontal(char[][] soup, WordPosition wordPos) {
        int size = soup.length;
        String word = wordPos.word;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= size - word.length(); j++) {
                String row = "";
                for (int k = j; k < j + word.length(); k++) {
                    row += soup[i][k];
                }
                if (row.equals(word)) {
                    wordPos.startRow = i + 1;
                    wordPos.startCol = j + 1;
                    markLetters(soup, word, i, j, Direction.DIREITA);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findWordVertical(char[][] soup, WordPosition wordPos) {
        int size = soup.length;
        String word = wordPos.word;

        for (int i = 0; i <= size - word.length(); i++) {
            for (int j = 0; j < size; j++) {
                String col = "";
                for (int k = i; k < i + word.length(); k++) {
                    col += soup[k][j];
                }
                if (col.equals(word)) {
                    wordPos.startRow = i + 1;
                    wordPos.startCol = j + 1;
                    markLetters(soup, word, i, j, Direction.BAIXO);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findWordHorizontalReverse(char[][] soup, WordPosition wordPos) {
        int size = soup.length;
        String word = wordPos.word;

        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= word.length() - 1; j--) {
                String row = "";
                for (int k = j; k > j - word.length(); k--) {
                    row += soup[i][k];
                }
                if (row.equals(word)) {
                    wordPos.startRow = i + 1;
                    wordPos.startCol = j + 1;
                    markLetters(soup, word, i, j, Direction.ESQUERDA);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findWordVerticalReverse(char[][] soup, WordPosition wordPos) {
        int size = soup.length;
        String word = wordPos.word;

        for (int i = size - 1; i >= word.length() - 1; i--) {
            for (int j = 0; j < size; j++) {
                String col = "";
                for (int k = i; k > i - word.length(); k--) {
                    col += soup[k][j];
                }
                if (col.equals(word)) {
                    wordPos.startRow = i + 1;
                    wordPos.startCol = j + 1;
                    markLetters(soup, word, i, j, Direction.CIMA);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findWordDiagonalUpLeft(char[][] soup, WordPosition wordPos) {
        int size = soup.length;
        String word = wordPos.word;

        for (int i = size - 1; i >= word.length() - 1; i--) {
            for (int j = size - 1; j >= word.length() - 1; j--) {
                String diagonal = "";
                for (int k = 0; k < word.length(); k++) {
                    diagonal += soup[i - k][j - k];
                }
                if (diagonal.equals(word)) {
                    wordPos.startRow = i + 1;
                    wordPos.startCol = j + 1;
                    markLetters(soup, word, i, j, Direction.DIAGONAL_CIMA_ESQUERDA);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findWordDiagonalUpRight(char[][] soup, WordPosition wordPos) {
        int size = soup.length;
        String word = wordPos.word;

        for (int i = size - 1; i >= word.length() - 1; i--) {
            for (int j = 0; j <= size - word.length(); j++) {
                String diagonal = "";
                for (int k = 0; k < word.length(); k++) {
                    diagonal += soup[i - k][j + k];
                }
                if (diagonal.equals(word)) {
                    wordPos.startRow = i + 1;
                    wordPos.startCol = j + 1;
                    markLetters(soup, word, i, j, Direction.DIAGONAL_CIMA_DIREITA);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findWordDiagonalDownLeft(char[][] soup, WordPosition wordPos) {
        int size = soup.length;
        String word = wordPos.word;

        for (int i = 0; i <= size - word.length(); i++) {
            for (int j = size - 1; j >= word.length() - 1; j--) {
                String diagonal = "";
                for (int k = 0; k < word.length(); k++) {
                    diagonal += soup[i + k][j - k];
                }
                if (diagonal.equals(word)) {
                    wordPos.startRow = i + 1;
                    wordPos.startCol = j + 1;
                    markLetters(soup, word, i, j, Direction.DIAGONAL_BAIXO_ESQUERDA);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findWordDiagonalDownRight(char[][] soup, WordPosition wordPos) {
        int size = soup.length;
        String word = wordPos.word;

        for (int i = 0; i <= size - word.length(); i++) {
            for (int j = 0; j <= size - word.length(); j++) {
                String diagonal = "";
                for (int k = 0; k < word.length(); k++) {
                    diagonal += soup[i + k][j + k];
                }
                if (diagonal.equals(word)) {
                    wordPos.startRow = i + 1;
                    wordPos.startCol = j + 1;
                    markLetters(soup, word, i, j, Direction.DIAGONAL_BAIXO_DIREITA);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isPartOfWord(WordPosition wordPos, int row, int col) {
        int startRow = wordPos.startRow - 1;
        int startCol = wordPos.startCol - 1;

        switch (wordPos.direction) {
            case DIREITA:
                return row == startRow && col >= startCol && col < startCol + wordPos.word.length();
            case BAIXO:
                return col == startCol && row >= startRow && row < startRow + wordPos.word.length();
            case CIMA:
                return col == startCol && row <= startRow && row > startRow - wordPos.word.length();
            case ESQUERDA:
                return row == startRow && col <= startCol && col > startCol - wordPos.word.length();
            case DIAGONAL_CIMA_ESQUERDA:
                return row <= startRow && row > startRow - wordPos.word.length() && col <= startCol && col > startCol - wordPos.word.length();
            case DIAGONAL_CIMA_DIREITA:
                return row <= startRow && row > startRow - wordPos.word.length() && col >= startCol && col < startCol + wordPos.word.length();
            case DIAGONAL_BAIXO_ESQUERDA:
                return row >= startRow && row < startRow + wordPos.word.length() && col <= startCol && col > startCol - wordPos.word.length();
            case DIAGONAL_BAIXO_DIREITA:
                return row >= startRow && row < startRow + wordPos.word.length() && col >= startCol && col < startCol + wordPos.word.length();
            default:
                return false;
        }
    }
}
