package ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextReader implements Filter {
    private final Scanner scanner;

    public TextReader(String filename) throws FileNotFoundException {
        this.scanner = new Scanner(new File(filename));
    }

    public boolean hasNext() {
        return this.scanner.hasNext();
    }

    public String next() {
        return this.scanner.nextLine();
    }
}
