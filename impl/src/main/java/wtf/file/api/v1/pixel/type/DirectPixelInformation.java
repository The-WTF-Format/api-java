package wtf.file.api.v1.pixel.type;

import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.v1.decoding.clut.ClutInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DirectPixelInformation extends PixelInformation {

    private final Map<ColorChannel, Short> channelValues;

    public DirectPixelInformation(int frame, int x, int y, Map<ColorChannel, Long> channelValues) {
        super(frame, x, y, PixelType.DIRECT_ENTRY);

        this.channelValues = new HashMap<>();

        channelValues.forEach((channel, value) -> this.channelValues.put(channel, value.shortValue()));
    }

    public Map<ColorChannel, Short> channelValues() {
        return channelValues;
    }

    @Override
    public Map<ColorChannel, Short> pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation, ArrayList<PixelInformation> visited) {
        return channelValues;
    }

}
