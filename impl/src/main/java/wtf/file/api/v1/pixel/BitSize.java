package wtf.file.api.v1.pixel;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.color.channel.FixedColorChannel;

public record BitSize(
        int channelWidth,
        int colorEntry,
        int clutCode,
        int frameReference,
        int xReference,
        int yReference
) {

    public static BitSize of(int channelWidth, ColorSpace colorSpace, int clutCode, int maxFrames, int height, int width) {
        return new BitSize(
                channelWidth,
                getBitSize(colorSpace, channelWidth),
                clutCode,
                getBitSize(maxFrames),
                getBitSize(width),
                getBitSize(height)
        );
    }

    private static int getBitSize(ColorSpace colorSpace, int channelWidth) {
        int colorEntry = 0;
        for (ColorChannel channel : colorSpace.channels()) {
            switch (channel.type()) {
                case FIXED -> colorEntry += ((FixedColorChannel) channel).bits();
                case DYNAMIC -> colorEntry += channelWidth;
            }
        }
        return colorEntry;
    }


    private static int getBitSize(int maxValue) {
        return 1 + (int) Math.floor(Math.log(maxValue) / Math.log(2));
    }

}
