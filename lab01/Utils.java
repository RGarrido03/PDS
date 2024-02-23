package lab01;

import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Direction randomDirection() {
        int x = RANDOM.nextInt(Direction.values().length);
        return Direction.values()[x];
    }

    public String capitalizeString(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
