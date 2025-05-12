package wtf.file.api.v1.pixel;

import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.WtfException;
import wtf.file.api.v1.decoding.clut.ClutInformation;

import java.util.ArrayList;
import java.util.Map;

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

    public Map<ColorChannel, Short> pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation) throws WtfException {
        return pixel(clutInformation, pixelInformation, new ArrayList<>());
    }

    public abstract Map<ColorChannel, Short> pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation, ArrayList<PixelInformation> visited) throws WtfException;

}
