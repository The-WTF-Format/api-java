package wtf.file.api.v1.pixel.type;

import wtf.file.api.data.Pixel;
import wtf.file.api.v1.decoding.clut.ClutInformation;
import wtf.file.api.v1.pixel.PixelInformation;
import wtf.file.api.v1.pixel.PixelType;

public class ClutEntryPixelInformation extends PixelInformation {

    private final long clutCode;

    public ClutEntryPixelInformation(int frame, int x, int y, long clutCode) {
        super(frame, x, y, PixelType.CLUT_ENTRY);
        this.clutCode = clutCode;
    }

    @Override
    public Pixel pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation) {
        return null;
    }
}
