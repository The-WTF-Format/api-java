package wtf.file.api.color.channel;

public record FixedColorChannel(String name, short bits) implements ColorChannel {

    @Override
    public ChannelType type() {
        return ChannelType.FIXED;
    }

}
