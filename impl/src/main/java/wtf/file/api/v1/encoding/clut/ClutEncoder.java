package wtf.file.api.v1.encoding.clut;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.NumberUtil;
import wtf.file.api.util.WriteBitStream;
import wtf.file.api.v1.encoding.data.ImageDataEncoder;
import wtf.file.api.v1.pixel.ImageData;

import java.util.Map;

public class ClutEncoder {

    public static int encode(ColorSpace colorSpace, int channelWidth, ImageData imageData, WriteBitStream bitStream) throws WtfException {
        Map<Long, Map<ColorChannel, Short>> clutEnties = imageData.clutEntries();
        if (clutEnties.isEmpty()) {
            bitStream.writeNumber(0, 8);
            return 0;
        }

        int codeLength = NumberUtil.getChannelWidth(clutEnties.keySet().stream().max(Long::compare).orElseThrow());
        bitStream.writeNumber(codeLength, 8);

        for (Map.Entry<Long, Map<ColorChannel, Short>> clutEntry : clutEnties.entrySet()) {
            long clutCode = clutEntry.getKey();
            Map<ColorChannel, Short> colorEntry = clutEntry.getValue();

            ImageDataEncoder.writeChannelValues(colorEntry, colorSpace, channelWidth, bitStream);
        }

        bitStream.writeNumber(0, codeLength);
        bitStream.padToByte();

        return codeLength;
    }

}
