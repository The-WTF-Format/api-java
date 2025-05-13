package wtf.file.api.v1.pixel.type;

import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.v1.decoding.clut.ClutInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClutEntryPixelInformation extends PixelInformation {

    private final int clutCode;

    public ClutEntryPixelInformation(int frame, int x, int y, long clutCode) {
        super(frame, x, y, PixelType.CLUT_ENTRY);
        this.clutCode = Math.toIntExact(clutCode);
    }

    public int clutCode() {
        return this.clutCode;
    }

    @Override
    public Map<ColorChannel, Short> pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation, ArrayList<PixelInformation> visited) {
        Map<ColorChannel, Integer> clutEntry = clutInformation.clut().get(this.clutCode);
        Map<ColorChannel, Short> pixelData = new HashMap<>();

        clutEntry.forEach((channel, value) -> pixelData.put(channel, value.shortValue()));

        return pixelData;
    }
}
