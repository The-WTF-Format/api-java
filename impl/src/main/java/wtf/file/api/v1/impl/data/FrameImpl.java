package wtf.file.api.v1.impl.data;

import wtf.file.api.data.Frame;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.NumberOutOfBoundsException;

import java.awt.*;

public class FrameImpl implements Frame {

    @Override
    public Pixel[][] pixels() {
        return new Pixel[0][];
    }

    @Override
    public Pixel at(int x, int y) throws NumberOutOfBoundsException {
        return null;
    }

    @Override
    public Image asJavaImage() {
        return null;
    }

}
