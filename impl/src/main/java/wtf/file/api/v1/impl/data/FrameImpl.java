package wtf.file.api.v1.impl.data;

import wtf.file.api.data.Frame;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.exception.NumberOutOfBoundsException;

import java.awt.*;

public class FrameImpl implements Frame {

    private final Pixel[][] pixels;

    public FrameImpl(Pixel[][] pixels) {
        this.pixels = pixels;
    }

    @Override
    public Pixel[][] pixels() {
        return pixels;
    }

    @Override
    public Pixel at(int x, int y) throws NumberOutOfBoundsException {
        if (x < 0 || x >= pixels.length) {
            throw new NumberOutOfBoundsException(0, pixels.length - 1, x);
        }

        if (y < 0 || y >= pixels[x].length) {
            throw new NumberOutOfBoundsException(0, pixels[x].length - 1, y);
        }

        return pixels[x][y];
    }

    @Override
    public Image asJavaImage() {
        throw new NotYetImplementedException();
    }

}
