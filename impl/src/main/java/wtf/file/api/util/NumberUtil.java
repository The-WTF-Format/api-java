package wtf.file.api.util;

import wtf.file.api.exception.NumberOutOfBoundsException;

public class NumberUtil {

    public static void checkBounds(int value, int min, int max, String name) {
        if (value < min || value > max) {
            throw new NumberOutOfBoundsException(min, max, value, name);
        }
    }

}
