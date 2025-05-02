package wtf.file.api.color.value;

public class ZeroDefaultValue implements DefaultValue {

    public static final ZeroDefaultValue INSTANCE = new ZeroDefaultValue();

    private ZeroDefaultValue() {
        // Prevent instantiation
    }

    @Override
    public Long forChannelWidth(short channelWidth) {
        return 0L;
    }



}
