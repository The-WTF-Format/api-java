package wtf.file.api.color.value;

public class MaxDefaultValue implements DefaultValue {

    public static final MaxDefaultValue INSTANCE = new MaxDefaultValue();

    private MaxDefaultValue() {
        // Prevent instantiation
    }


    @Override
    public Long forChannelWidth(short channelWidth) {
        return (1L << channelWidth) - 1;
    }

}
