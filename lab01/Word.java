package lab01;

import java.io.PrintWriter;
import java.util.List;

public class Word {
    private final String word;
    private int row;
    private int col;
    private Direction direction;

    public Word(String word) {
        this.word = word;
    }

    public Word(String word, int row, int col, Direction direction) {
        this.word = word;
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    /**
     * <b>Print the word list</b> to <code>System.out</code>.
     * <p>
     * Output: Word | Length | Position | Direction
     *
     * @param words List of words.
     */
    public static void printListOfWords(List<Word> words) {
        printListOfWords(words, null);
    }

    /**
     * <b>Print the word list</b> to <code>System.out</code> and a file.
     * <p>
     * Output: Word | Length | Position | Direction
     *
     * @param words List of words.
     * @param out   File to write.
     */
    public static void printListOfWords(List<Word> words, PrintWriter out) {
        for (Word word : words) {
            if (out != null) out.println(word.toString());
            System.out.println(word);
        }
    }

    public String getWord() {
        return word;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getLength() {
        return word.length();
    }

    @Override
    public String toString() {
        return String.format("%-15s%-10d%-10s%-15s", this.getWord(), this.getLength(),
                             this.getRow() + "," + this.getCol(),
                             Utils.capitalizeString(this.getDirection().toString()));
    }
}
