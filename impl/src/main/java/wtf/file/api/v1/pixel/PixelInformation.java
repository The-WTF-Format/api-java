package wtf.file.api.v1.pixel;

import wtf.file.api.data.Pixel;
import wtf.file.api.v1.decoding.clut.ClutInformation;

public abstract class PixelInformation {

    protected final int frame;
    protected final int x;
    protected final int y;
    protected final PixelType type;

    protected PixelInformation(int frame, int x, int y, PixelType type) {
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public abstract Pixel pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation);

}
