package wtf.file.api.v1.decoding.header;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.ReadBitStream;
import wtf.file.api.v1.util.header.ColorSpaceUtil;

public class HeaderDecoder {

    @NotNull
    public static HeaderInformation decode(ReadBitStream bitStream) throws WtfException {
        int height = Math.toIntExact(bitStream.readNumber(16));
        int width = Math.toIntExact(bitStream.readNumber(16));

        byte colorSpaceByte = bitStream.readByte();
        ColorSpace colorSpace = ColorSpaceUtil.colorSpaceMap().get(bitStream.readByte());
        if (colorSpace == null) {
            throw new WtfException(String.format("Unknown color space %X", colorSpaceByte));
        }

        int channelWidth = Math.toIntExact(bitStream.readNumber(8));
        if (channelWidth > 8) {
            throw new WtfException(String.format("Channel width %d is greater than 8", channelWidth));
        }

        int frames = Math.toIntExact(bitStream.readNumber(8));
        if (frames == 0) {
            throw new WtfException("Frames (0) must be at least 1");
        }

        HeaderInformation.FrameCoding frameCoding = HeaderInformation.FrameCoding.fromTFlag(bitStream.readFlag());
        int timingValue = Math.toIntExact(bitStream.readNumber(7));

        ensureCorrectAnimation(frames, frameCoding, timingValue);

        return new HeaderInformation(
                height, width,
                colorSpace, channelWidth,
                frames, frameCoding, timingValue
        );
    }

    private static void ensureCorrectAnimation(int frames, HeaderInformation.FrameCoding frameCoding, int timingValue) throws WtfException {
        if (frames == 1) {
            if (frameCoding != HeaderInformation.FrameCoding.FPS_CODED) {
                throw new WtfException("If frames is 1, t must be 0 but was 1");
            }

            if (timingValue != 0) {
                throw new WtfException(String.format("If frames is 1, frame timing must be 0, got %d", timingValue));
            }
        } else {
            if (timingValue == 0) {
                throw new WtfException("If frames is 1, fps or spf must not be 0");
            }
        }
    }


}
