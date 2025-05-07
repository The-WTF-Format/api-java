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

        byte[] heightBytes = Arrays.copyOfRange(header, 0, 2);
        int height = (heightBytes[0] & 0xFF) << 8 | heightBytes[1] & 0xFF;

        byte[] widthBytes = Arrays.copyOfRange(header, 2, 4);
        int width = (widthBytes[0] & 0xFF) << 8 | widthBytes[1] & 0xFF;

        byte colorSpaceByte = header[4];
        ColorSpace colorSpace = ColorSpaceUtil.colorSpaceMap().get(colorSpaceByte);
        if (colorSpace == null) {
            throw new WtfException(String.format("Unknown color space %d", colorSpaceByte));
        }

        byte channelWidthByte = header[5];
        int channelWidth = channelWidthByte & 0xFF;

        byte framesByte = header[6];
        int frames = framesByte & 0xFF;

        byte timingByte = header[7];
        HeaderInformation.FrameCoding timing = HeaderInformation.FrameCoding.fromTFlag((timingByte & 0x80) != 0);
        int timingValue = timingByte & 0x7F;

        return new Pair<>(
                new HeaderInformation(
                        height, width,
                        colorSpace, channelWidth,
                        frames, timing, timingValue
                ),
                remaining
        );
    }

}
