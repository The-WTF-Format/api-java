package wtf.file.api.v1.pixel.type;

import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.NumberUtil;
import wtf.file.api.v1.decoding.clut.ClutInformation;

import java.util.ArrayList;
import java.util.Map;

public class ReferencePixelInformation extends PixelInformation {

    private final int frameReference;
    private final int xReference;
    private final int yReference;

    public ReferencePixelInformation(int frame, int x, int y, PixelType type, int frameReference, int xReference, int yReference) throws WtfException {
        super(frame, x, y, type);

        this.frameReference = getFrameReference(frame, x, y, frameReference);

        if (xReference == -2 && yReference == -2) {
            this.xReference = x;
            this.yReference = y;
        } else  if (xReference == -1 && yReference == -1) {
            this.xReference = x == 0 ? Integer.MAX_VALUE : x - 1;
            this.yReference = x == 0 ? y - 1 : y;
        } else {
            this.xReference = xReference;
            this.yReference = yReference;
        }
    }

    public int frameReference() {
        return frameReference;
    }

    public int xReference() {
        return xReference;
    }

    public int yReference() {
        return yReference;
    }

    private int getFrameReference(int frame, int x, int y, int frameReference) throws WtfException {
        int actualFrameReference = frameReference;
        if (actualFrameReference == -1) {
            if (frame == 0) {
                throw new WtfException(String.format("Cannot reference previous frame from frame 0 at [%d;%d;%d]", frame, x, y));
            }

            actualFrameReference = frame - 1;
        }
        if (actualFrameReference == -2) {
            actualFrameReference = frame;
        }
        if (actualFrameReference < 0) {
            throw new WtfException(String.format("Cannot reference frame %d from [%d;%d;%d]", actualFrameReference, frame, x, y));
        }
        return actualFrameReference;
    }

    @Override
    public Map<ColorChannel, Short> pixel(ClutInformation clutInformation, PixelInformation[][][] pixelInformation, ArrayList<PixelInformation> visited) throws WtfException {
        if (visited.contains(this)) {
            throw new WtfException(String.format("Cyclic reference at [%d;%d;%d]", frame, x, y));
        }

        visited.add(this);
        NumberUtil.checkBounds(frameReference, 0, pixelInformation.length - 1, "frameReference");
        NumberUtil.checkBounds(xReference, 0, pixelInformation[frameReference].length - 1, "xReference");
        NumberUtil.checkBounds(yReference, 0, pixelInformation[frameReference][xReference].length - 1, "yReference");

        PixelInformation pixelInformationReference = pixelInformation[frameReference][xReference][yReference];
        if (pixelInformationReference != null) {
            return pixelInformationReference.pixel(clutInformation, pixelInformation, visited);
        }

        throw new WtfException(String.format("Reference pixel at [%d;%d;%d] is null", frame, x, y));
    }
}
