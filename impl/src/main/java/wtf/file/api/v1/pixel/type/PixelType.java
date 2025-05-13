package wtf.file.api.v1.pixel.type;

public enum PixelType {

    DIRECT_ENTRY((byte) 0x00),
    COPY_BY_LOCATION((byte) 0x01),
    COPY_BY_FRAME((byte) 0x02),
    COPY_BY_FRAME_AND_LOCATION((byte) 0x03),
    COPY_PREVIOUS_LOCATION((byte) 0x04),
    COPY_PREVIOUS_FRAME((byte) 0x05),
    CLUT_ENTRY((byte) 0x06);

    private static final byte bits = 3;

    private final byte flag;

    PixelType(byte flag) {
        this.flag = flag;
    }

    public byte flag() {
        return flag;
    }

    public static PixelType fromFlag(byte flag) {
        for (PixelType pixelType : values()) {
            if (pixelType.flag() == flag) {
                return pixelType;
            }
        }

        return null;
    }

}
