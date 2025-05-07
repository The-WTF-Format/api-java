package wtf.file.api.v1.util.header;

import wtf.file.api.color.ColorSpace;

import java.util.HashMap;
import java.util.Map;

public class ColorSpaceUtil {

    private static Map<Byte, ColorSpace> colorSpaceMap;

    public static Map<Byte, ColorSpace> colorSpaceMap() {
        if (colorSpaceMap == null) {
            colorSpaceMap = new HashMap<>();

            colorSpaceMap.put((byte) 0x00, ColorSpace.GRAY_SCALE);
            colorSpaceMap.put((byte) 0x01, ColorSpace.GRAY_SCALE_A);
            colorSpaceMap.put((byte) 0x02, ColorSpace.DYNAMIC_GRAY_SCALE_A);
            colorSpaceMap.put((byte) 0x10, ColorSpace.RGB);
            colorSpaceMap.put((byte) 0x11, ColorSpace.RGBa);
            colorSpaceMap.put((byte) 0x12, ColorSpace.DYNAMIC_RGBa);
            colorSpaceMap.put((byte) 0x20, ColorSpace.CMY);
            colorSpaceMap.put((byte) 0x21, ColorSpace.CMYa);
            colorSpaceMap.put((byte) 0x22, ColorSpace.DYNAMIC_CMYa);
            colorSpaceMap.put((byte) 0x30, ColorSpace.HSV);
            colorSpaceMap.put((byte) 0x31, ColorSpace.HSVa);
            colorSpaceMap.put((byte) 0x32, ColorSpace.DYNAMIC_HSVa);
            colorSpaceMap.put((byte) 0x40, ColorSpace.YCbCr);
            colorSpaceMap.put((byte) 0x41, ColorSpace.YCbCra);
            colorSpaceMap.put((byte) 0x42, ColorSpace.DYNAMIC_YCbCra);
        }

        return colorSpaceMap;
    }

}
