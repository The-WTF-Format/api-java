package wtf.file.api.v1.pixel.type;

import org.apache.commons.lang3.ArrayUtils;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.ReadBitStream;
import wtf.file.api.v1.decoding.clut.ClutInformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DirectPixelInformation extends PixelInformation {

    private final Map<ColorChannel, Short> channelValues;

    private Long code = null;

    public DirectPixelInformation(int frame, int x, int y, Map<ColorChannel, Short> channelValues) {
        super(frame, x, y, PixelType.DIRECT_ENTRY);
        this.channelValues = Collections.unmodifiableMap(channelValues);
    }

    public Map<ColorChannel, Short> channelValues() {
        return channelValues;
    }

    @Override
    public Map<ColorChannel, Short> pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation, ArrayList<PixelInformation> visited) {
        return channelValues;
    }

    public long code() throws WtfException {
        if (code == null) {
            List<Byte> bytes = new ArrayList<>();
            channelValues.forEach((channel, value) -> {
                bytes.add((byte) value.shortValue());
                bytes.add((byte) (value >> 8));
            });
            byte[] codeArray = ArrayUtils.toPrimitive(bytes.toArray(new Byte[0]));

            code = new ReadBitStream(codeArray).readNumber(codeArray.length * 8);
        }

        return code;
    }

}
