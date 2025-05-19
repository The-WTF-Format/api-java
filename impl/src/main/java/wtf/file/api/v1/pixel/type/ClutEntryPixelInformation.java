package wtf.file.api.v1.pixel.type;

import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.v1.decoding.clut.ClutInformation;

import java.util.ArrayList;
import java.util.Map;

public class ClutEntryPixelInformation extends PixelInformation {

    private final long clutCode;
    private final long code;

    public ClutEntryPixelInformation(int frame, int x, int y, long clutCode, long code) {
        super(frame, x, y, PixelType.CLUT_ENTRY);
        this.clutCode = Math.toIntExact(clutCode);
        this.code = code;
    }

    public ClutEntryPixelInformation(int frame, int x, int y, long clutCode) {
        this(frame, x, y, clutCode, 0);
    }

    public long clutCode() {
        return this.clutCode;
    }

    public long code() {
        return this.code;
    }

    @Override
    public Map<ColorChannel, Short> pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation, ArrayList<PixelInformation> visited) {
        return clutInformation.clut().get(this.clutCode);
    }
}
