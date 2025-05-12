package wtf.file.api.v1.pixel.type;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;
import wtf.file.api.v1.decoding.clut.ClutInformation;
import wtf.file.api.v1.pixel.PixelInformation;
import wtf.file.api.v1.pixel.PixelType;

import java.util.Map;

public class DirectPixelInformation extends PixelInformation {

    public DirectPixelInformation(int frame, int x, int y, ColorSpace space, Map<ColorChannel, Long> channelValues) {
        super(frame, x, y, PixelType.DIRECT_ENTRY);
    }

    @Override
    public Pixel pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation) {
        return null;
    }

}
