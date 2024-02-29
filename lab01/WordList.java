package lab01;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class WordList implements Iterable<Word> {
    private final List<Word> words = new ArrayList<>();

    public void addWord(Word word) {
        this.words.add(word);
    }

    public void addAll(List<Word> words) {
        this.words.addAll(words);
    }

    /**
     * <b>Print the word list</b> to <code>System.out</code>.
     * <p>
     * If <code>includeProps</code> is true, the output for each word will be as follows:
     * <i>Word | Length | Position | Direction</i>. Otherwise, only the word is printed.
     *
     * @param includeProps Whether full props must be included or not.
     */
    public void printWordList(boolean includeProps) {
        printWordList(null, includeProps);
    }

    /**
     * <b>Print the word list</b> to <code>System.out</code> and a file.
     * <p>
     * If <code>includeProps</code> is true, the output for each word will be as follows:
     * <i>Word | Length | Position | Direction</i>. Otherwise, only the word is printed.
     *
     * @param out          File to write.
     * @param includeProps Whether full props must be included or not.
     */
    public void printWordList(PrintWriter out, boolean includeProps) {
        for (Word word : this.words) {
            if (includeProps) {
                System.out.println(word);
                if (out != null) out.println(word);
            } else {
                System.out.println(word.getWord());
                if (out != null) out.println(word.getWord());
            }
        }
    }

    @Override
    public Iterator<Word> iterator() {
        return this.words.iterator();
    }

    @Override
    public void forEach(Consumer<? super Word> action) {
        Iterable.super.forEach(action);
    }
}
