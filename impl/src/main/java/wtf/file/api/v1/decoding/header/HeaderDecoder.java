package wtf.file.api.v1.decoding.header;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.Pair;
import wtf.file.api.v1.util.header.ColorSpaceUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HeaderDecoder {

    @NotNull
    public static Pair<HeaderInformation, byte[]> decode(byte[] bytes) throws WtfException {
        // header has 8 bytes
        if (bytes.length < 8) {
            throw new WtfException(String.format("Header fields require at least 8 bytes, got %d", bytes.length));
        }

        byte[] header = Arrays.copyOfRange(bytes, 0, 8);
        byte[] remaining = Arrays.copyOfRange(bytes, 8, bytes.length);

        int height = getHeight(header);
        int width = getWidth(header);

        ColorSpace colorSpace = getColorSpace(header);
        int channelWidth = getChannelWidth(header);

        int frames = getFrames(header);

        byte timingByte = header[7];
        HeaderInformation.FrameCoding timing = getFrameCoding(timingByte);
        int timingValue = getTimingValue(timingByte);

        ensureCorrectAnimation(frames, timing, timingValue);

        return new Pair<>(
                new HeaderInformation(
                        height, width,
                        colorSpace, channelWidth,
                        frames, timing, timingValue
                ),
                remaining
        );
    }

    private static int getHeight(byte[] header) {
        byte[] heightBytes = Arrays.copyOfRange(header, 0, 2);
        return (heightBytes[0] & 0xFF) << 8 | heightBytes[1] & 0xFF;
    }

    private static int getWidth(byte[] header) {
        byte[] widthBytes = Arrays.copyOfRange(header, 2, 4);
        return (widthBytes[0] & 0xFF) << 8 | widthBytes[1] & 0xFF;
    }

    @NotNull
    private static ColorSpace getColorSpace(byte[] header) throws WtfException {
        byte colorSpaceByte = header[4];
        ColorSpace colorSpace = ColorSpaceUtil.colorSpaceMap().get(colorSpaceByte);
        if (colorSpace == null) {
            throw new WtfException(String.format("Unknown color space %X", colorSpaceByte));
        }

        return colorSpace;
    }

    private static int getChannelWidth(byte[] header) throws WtfException {
        byte channelWidthByte = header[5];
        int channelWidth = channelWidthByte & 0xFF;
        if (channelWidth > 8) {
            throw new WtfException(String.format("Channel width %d is greater than 8", channelWidth));
        }
        return channelWidth;
    }

    private static int getFrames(byte[] header) throws WtfException {
        byte framesByte = header[6];
        int frames = framesByte & 0xFF;
        if (frames == 0) {
            throw new WtfException("Frames (0) must be at least 1");
        }
        return frames;
    }

    private static HeaderInformation.FrameCoding getFrameCoding(byte timingByte) {
        return HeaderInformation.FrameCoding.fromTFlag((timingByte & 0x80) != 0);
    }

    private static int getTimingValue(byte timingByte) {
        return timingByte & 0x7F;
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
