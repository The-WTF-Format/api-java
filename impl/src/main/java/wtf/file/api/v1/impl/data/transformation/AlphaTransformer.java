package wtf.file.api.v1.impl.data.transformation;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.color.channel.FixedColorChannel;
import wtf.file.api.color.value.MaxDefaultValue;
import wtf.file.api.color.value.ZeroDefaultValue;

public class AlphaTransformer {

    public static final AlphaTransformer INSTANCE = new AlphaTransformer();

    private AlphaTransformer() {}

    public short transform(short alpha, int channelWidth, ColorSpace from, ColorSpace to) {
        ColorChannel fromChannel = getAlphaChannel(from), toChannel = getAlphaChannel(to);

        if (fromChannel.equals(toChannel)) {
            return alpha;
        }

        // from fixed to dynamic
        if (fromChannel instanceof FixedColorChannel) {
            if (alpha >= 1) return MaxDefaultValue.INSTANCE.forChannelWidth(channelWidth);
            else return ZeroDefaultValue.INSTANCE.forChannelWidth(channelWidth);
        }

        // from dynamic to fixed
        int threshold = (int) (Math.pow(2, channelWidth) - 1) / 2;
        if (alpha >= threshold) return 1;
        else return 0;
    }

    public ColorChannel getAlphaChannel(ColorSpace colorSpace) {
        return switch (colorSpace) {
            case GRAY_SCALE, RGB, CMY, HSV, YCbCr -> null;
            default -> colorSpace.channels().getLast();
        };
    }

}
