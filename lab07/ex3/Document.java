package ex3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Document {
    private String text;

    public Document(String path) {
        try (Scanner scanner = new Scanner(new FileReader(path))) {
            this.text = scanner.useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            this.text = "";
        }
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text.substring(0, 15) + "...";
    }
}
