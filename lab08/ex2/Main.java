package ex2;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            Filter filter = new CapitalizationFilter(new TermFilter(new TextReader("ex2/input.txt")));
            while (filter.hasNext()) {
                System.out.println(filter.next());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.exit(1);
        }
    }
}
