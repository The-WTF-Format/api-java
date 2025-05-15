package wtf.file.api.v1.impl.data;

import wtf.file.api.data.Frame;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.util.NumberUtil;

import java.awt.*;

public record FrameImpl(Pixel[][] pixels) implements Frame {

    @Override
    public Pixel at(int x, int y) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(x, 0, pixels.length - 1, "x");
        NumberUtil.checkBounds(y, 0, pixels[0].length - 1, "y");

        return pixels[x][y];
    }

    @Override
    public Image asJavaImage() {
        throw new NotYetImplementedException();
    }

}
