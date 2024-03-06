package lab3.ex2.utils;

import java.util.Scanner;

public class ScannerParser {
    public static int parseInt(Scanner sc, Error error) {
        if (!sc.hasNextInt()) {
            throw new IllegalArgumentException(error.toString());
        }
        return Integer.parseInt(sc.next());
    }

    public static String parseRegexString(Scanner sc, String pattern, Error error) {
        if (!sc.hasNext(pattern)) {
            throw new IllegalArgumentException(error.toString());
        }
        return sc.next();
    }
}
