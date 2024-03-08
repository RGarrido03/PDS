package lab3.ex2.utils;

import lab3.ex2.ClassType;

import java.util.Scanner;

public class ScannerParser {
    public static int parseInt(Scanner sc, Error error) {
        if (!sc.hasNextInt()) {
            throw new IllegalArgumentException(error.toString());
        }
        return Integer.parseInt(sc.next());
    }

    public static String parseRegexString(Scanner sc, String pattern, Error error) {
        return parseRegexString(sc, pattern, error, false);
    }

    public static String parseRegexString(Scanner sc, String pattern, Error error, boolean optional) {
        if (!sc.hasNext(pattern)) {
            if (optional) {
                return null;
            }
            throw new IllegalArgumentException(error.toString());
        }
        return sc.next();
    }

    public static ClassType parseClassType(Scanner sc) {
        for (ClassType ct : ClassType.values()) {
            if (sc.hasNext(ct.toString())) {
                sc.next();
                return ct;
            }
        }
        throw new IllegalArgumentException(Error.INVALID_CLASS_TYPE.toString());
    }
}
