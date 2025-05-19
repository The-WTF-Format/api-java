package wtf.file.api.v1.decoding.clut;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.ReadBitStream;
import wtf.file.api.v1.decoding.data.ImageDataDecoder;
import wtf.file.api.v1.decoding.header.HeaderInformation;

import java.util.HashMap;
import java.util.Map;

public class ClutDecoder {

    @NotNull
    public static ClutInformation decode(HeaderInformation headerInformation, ReadBitStream bitStream) throws WtfException {
        int codeLength = Math.toIntExact(bitStream.readNumber(8));
        if (codeLength == 0) {
            return new ClutInformation(0, Map.of());
        }

        Map<Long, Map<ColorChannel, Short>> clut = new HashMap<>();
        while (true) {
            long code = bitStream.readNumber(codeLength);
            if (code == 0) {
                bitStream.skipBytePadding();
                break;
            }

            Map<ColorChannel, Short> channelValues = ImageDataDecoder.readChannelMap(headerInformation, bitStream);
            clut.put(code, channelValues);
        }

        return new ClutInformation(codeLength, clut);
    }

}
