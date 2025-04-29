package wtf.file.api.color.channel;

public record FixedColorChannel(short bits, String name) implements ColorChannel {

    @Override
    public ChannelType type() {
        return ChannelType.FIXED;
    }

}
