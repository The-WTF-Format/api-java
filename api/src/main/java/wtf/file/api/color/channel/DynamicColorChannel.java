package wtf.file.api.color.channel;

public record DynamicColorChannel(String name) implements ColorChannel {

    @Override
    public ChannelType type() {
        return ChannelType.DYNAMIC;
    }

}
