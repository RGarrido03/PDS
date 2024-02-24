package lab01;

import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();

    /**<b>Check if string has numeric elements</b>
     *
     * @param str
     * @return str numeric or not
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**<b> Check if line has UpperCase letters</b>
     *
     * @param line
     * @return line is UpperCase or not
     */
    public static boolean isLineUpperCase(String line) {
        return line.equals(line.toUpperCase());
    }

    /**<b> Set Random Direction</b>
     * <p>
     *     Used in WSGenerator to set random direction to each new element
     * </p>
     * @return direction coordinates
     */
    public static Direction randomDirection() {
        int x = RANDOM.nextInt(Direction.values().length);
        return Direction.values()[x];
    }

    /**
     * <b>Random Char</b>
     * <p>
     *     Used in WSGenerator to set a random character to each new element
     * </p>
     * @return
     */
    public static char randomChar() {
        return (char) (RANDOM.nextInt(26) + 'A');
    }

    /**
     * <b> Turns first element in a String to UpperCase</b>
     * @param string
     * @return String with first element in UpperCase
     */
    public static String capitalizeString(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
