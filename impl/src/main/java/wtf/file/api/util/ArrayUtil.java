package wtf.file.api.util;

import java.util.function.Function;

public class ArrayUtil {

    public static <T> T[][] saveCopy(T[][] array, Function<Integer, T[][]> newArray) {
        T[][] saveCopy = newArray.apply(array.length);
        for (int i = 0; i < array.length; i++) {
            saveCopy[i] = array[i].clone();
        }

        return saveCopy;
    }

}
