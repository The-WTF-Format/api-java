package wtf.file.api.v1.pixel.type;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;
import wtf.file.api.v1.decoding.clut.ClutInformation;
import wtf.file.api.v1.impl.data.PixelImpl;
import wtf.file.api.v1.pixel.PixelInformation;
import wtf.file.api.v1.pixel.PixelType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DirectPixelInformation extends PixelInformation {

    final Map<ColorChannel, Short> channelValues;

    public DirectPixelInformation(int frame, int x, int y, Map<ColorChannel, Long> channelValues) {
        super(frame, x, y, PixelType.DIRECT_ENTRY);

        this.channelValues = new HashMap<>();

        channelValues.forEach((channel, value) -> this.channelValues.put(channel, value.shortValue()));
    }

    @Override
    public Map<ColorChannel, Short> pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation, ArrayList<PixelInformation> visited) {
        return channelValues;
    }

}
