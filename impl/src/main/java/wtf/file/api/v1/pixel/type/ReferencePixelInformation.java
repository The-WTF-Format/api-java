package wtf.file.api.v1.pixel.type;

import wtf.file.api.data.Pixel;
import wtf.file.api.v1.decoding.clut.ClutInformation;
import wtf.file.api.v1.pixel.PixelInformation;
import wtf.file.api.v1.pixel.PixelType;

public class ReferencePixelInformation extends PixelInformation {
    
    private final int frameReference;
    private final int xReference;
    private final int yReference;
    
    public ReferencePixelInformation(int frame, int x, int y, PixelType type, int frameReference, int xReference, int yReference) {
        super(frame, x, y, type);
        this.frameReference = frameReference;
        this.xReference = xReference;
        this.yReference = yReference;
    }

    @Override
    public Pixel pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation) {
        return null;
    }
}
