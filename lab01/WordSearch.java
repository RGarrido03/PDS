package lab01;

import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

public class WordSearch {
    private final char[][] puzzle;

    /**
     * Create a word search instance, from a <code>char[][]</code>.
     */
    public WordSearch(char[][] puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Create an empty NxN word search instance, with '.' as a default value for all positions.
     *
     * @param size Desired size
     */
    public WordSearch(int size) {
        char[][] puzzle = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                puzzle[i][j] = '.';
            }
        }
        this.puzzle = puzzle;
    }

    public int getSize() {
        return puzzle.length;
    }

    public char getChar(int row, int col) {
        return puzzle[row][col];
    }

    /**
     * <b>Print the puzzle</b> to <code>System.out</code>.
     */
    public void printPuzzle() {
        printPuzzle(null);
    }

    /**
     * <b>Print the puzzle</b> to both <code>System.out</code> and a file.
     *
     * @param out File to write.
     */
    public void printPuzzle(PrintWriter out) {
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                System.out.print(this.getChar(i, j));
                if (out != null) out.print(this.getChar(i, j));
            }
            System.out.println();
            if (out != null) out.println();
        }
    }

    /**
     * <b>Get next row.</b>
     * <p>
     * Given a row and a direction, return the next row.
     * Returns the same row if it exceeds the puzzle boundaries.
     * Useful for iterating over a word.
     *
     * @param row       Current row.
     * @param direction Desired direction.
     * @return Next row.
     */
    public int getNextRow(int row, Direction direction) {
        return switch (direction) {
            case up, upLeft, upRight -> row == 0 ? row : row - 1;
            case down, downLeft, downRight -> row == this.getSize() - 1 ? row : row + 1;
            case left, right -> row;
        };
    }

    /**
     * <b>Get next column.</b>
     * <p>
     * Given a column and a direction, return the next column.
     * Returns the same column if it exceeds the puzzle boundaries.
     * Useful for iterating over a word.
     *
     * @param col       Current column.
     * @param direction Desired direction.
     * @return Next column.
     */
    public int getNextCol(int col, Direction direction) {
        return switch (direction) {
            case up, down -> col;
            case left, upLeft, downLeft -> col == 0 ? col : col - 1;
            case right, upRight, downRight -> col == this.getSize() - 1 ? col : col + 1;
        };
    }

    /**
     * <b>Update a word in the puzzle.</b>
     * <p>
     * This function takes a word and recursively adds a word, letter by letter, to the puzzle.
     *
     * @param word      Word to update.
     * @param row       Row to update (first letter index if first called)
     * @param col       Column to update (first letter index if first called)
     * @param direction Word direction
     */
    public void addWord(String word, int row, int col, Direction direction) {
        if (word.isEmpty()) {
            return;
        }

        puzzle[row][col] = Character.toUpperCase(word.charAt(0));

        int nextRow = getNextRow(row, direction);
        int nextCol = getNextCol(col, direction);

        addWord(word.substring(1), nextRow, nextCol, direction);
    }

    /**
     * <b>Get safe row boundaries for adding a word.</b>
     * <p>
     * Depending on the position, a word may overflow the puzzle.
     * This function gets the safe row boundaries so that the word fits in it.
     *
     * @param direction Desired direction.
     * @param length    Word length.
     * @return Array with two ints: the start and end positions.
     */
    public int[] getRowBoundaries(Direction direction, int length) {
        int difference = this.getSize() - length;
        int end = this.getSize() - 1;

        return switch (direction) {
            case up, upLeft, upRight -> new int[]{end - difference, end};
            case down, downLeft, downRight -> new int[]{0, difference};
            case left, right -> new int[]{0, end};
        };
    }

    /**
     * <b>Get safe column boundaries for adding a word.</b>
     * <p>
     * Depending on the position, a word may overflow the puzzle.
     * This function gets the safe column boundaries so that the word fits in it.
     *
     * @param direction Desired direction.
     * @param length    Word length.
     * @return Array with two ints: the start and end positions.
     */
    public int[] getColBoundaries(Direction direction, int length) {
        int difference = this.getSize() - length;
        int end = this.getSize() - 1;

        return switch (direction) {
            case up, down -> new int[]{0, end};
            case left, upLeft, downLeft -> new int[]{end - difference, end};
            case right, upRight, downRight -> new int[]{0, difference};
        };
    }

    /**
     * <b>Check if a word wrongly overlaps the puzzle.</b>
     * <p>
     * When creating a word search puzzle and adding words to it, words may overlap each other on some positions,
     * causing one of the them to be wrongly written.
     * <p>
     * This function evaluates if this situation happens.
     *
     * @param word      Desired word.
     * @param row       Current row.
     * @param col       Current column.
     * @param direction Word direction.
     * @return Whether it overlaps other words or not.
     */
    public boolean isWordOverlapping(String word, int row, int col, Direction direction) {
        if (word.isEmpty()) {
            return false;
        }

        char posChar = this.getChar(row, col);
        if (posChar != '.' && posChar != word.charAt(0)) {
            return true;
        }

        int nextRow = getNextRow(row, direction);
        int nextCol = getNextCol(row, direction);

        return isWordOverlapping(word.substring(1), nextRow, nextCol, direction);
    }

    /**
     * <b>Get a position for a word</b>.
     * <p>
     * This function generates a position and updates the word passed as argument.
     * <p>
     * WARNING:
     * This function doesn't take other words into account yet.
     * Words will probably overlap between each other!
     *
     * @param word Word that needs a position in the puzzle.
     */
    public void generatePosition(Word word) {
        int row, col;
        Direction direction;

        do {
            direction = Utils.randomDirection();

            int[] rowBoundaries = this.getRowBoundaries(direction, word.getLength());
            int[] colBoundaries = this.getColBoundaries(direction, word.getLength());

            row = ThreadLocalRandom.current().nextInt(rowBoundaries[0], rowBoundaries[1] + 1);
            col = ThreadLocalRandom.current().nextInt(colBoundaries[0], colBoundaries[1] + 1);
        } while (isWordOverlapping(word.getWord(), row, col, direction));

        word.setRow(row);
        word.setCol(col);
        word.setDirection(direction);
    }
}
