package wtf.file.api.util;

public class ByteArrayUtil {

    public static boolean checkEmptyUpperBytes(byte[] bytes, int empty) {
        for (int i = 0; i < empty; i++) {
            if (bytes[i] != 0) {
                return false;
            }
        }

        return true;
    }

}
