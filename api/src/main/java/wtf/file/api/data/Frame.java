package wtf.file.api.data;

import wtf.file.api.exception.NumberOutOfBoundsException;

import java.awt.*;

public interface Frame {

    Pixel[][] pixels();

    /**
     *
     * @param x
     * @param y
     * @return
     * @throws NumberOutOfBoundsException
     */
    Pixel at(int x, int y);

    Image asJavaImage();

}
