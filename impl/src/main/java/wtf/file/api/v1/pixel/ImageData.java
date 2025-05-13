package wtf.file.api.v1.pixel;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.WtfException;
import wtf.file.api.v1.decoding.clut.ClutInformation;
import wtf.file.api.v1.impl.data.PixelImpl;
import wtf.file.api.v1.pixel.type.PixelInformation;

import java.util.Map;

public record ImageData(
    PixelInformation[][][] pixels,
    Map<Integer, Map<ColorChannel, Short>> clutEntries
) {

    public Pixel[][][] dereference(ColorSpace colorSpace, ClutInformation clutInformation) throws WtfException {
        Pixel[][][] dereferencedPixels = new Pixel[pixels.length][pixels[0].length][pixels[0][0].length];

        for (int frame = 0; frame < pixels.length; frame++) {
            for (int x = 0; x < pixels[frame].length; x++) {
                for (int y = 0; y < pixels[frame][x].length; y++) {
                    dereferencedPixels[frame][x][y] = new PixelImpl(colorSpace, pixels[frame][x][y].pixel(clutInformation, pixels));
                }
            }
        }

        return dereferencedPixels;
    }

}
