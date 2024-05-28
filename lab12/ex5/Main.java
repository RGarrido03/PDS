package ex5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        boolean recursive = false;
        String directoryPath = ".";

        for (String arg : args) {
            if (arg.equals("-r")) {
                recursive = true;
                continue;
            }
            directoryPath = arg;
        }

        Path startPath = Paths.get(directoryPath);
        try {
            DirectorySizeCalculator visitor = new DirectorySizeCalculator(startPath, recursive);
            Files.walkFileTree(startPath, visitor);
            System.out.println("Total: " + visitor.getSize() + " kB");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
