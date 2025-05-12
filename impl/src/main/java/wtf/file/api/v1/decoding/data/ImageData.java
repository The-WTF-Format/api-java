package wtf.file.api.v1.decoding.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.WtfException;
import wtf.file.api.v1.decoding.clut.ClutInformation;
import wtf.file.api.v1.impl.data.PixelImpl;
import wtf.file.api.v1.pixel.PixelInformation;

public record ImageData(PixelInformation[][][] pixels) {

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
