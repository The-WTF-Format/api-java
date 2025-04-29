package wtf.file.api.editable.data;

import wtf.file.api.data.Frame;
import wtf.file.api.exception.NumberOutOfBoundsException;

import java.awt.*;

public interface EditableFrame extends Frame {

    /**
     *
     * @param x
     * @param y
     * @return
     * @throws NumberOutOfBoundsException
     */
    @Override
    EditablePixel at(int x, int y);

    void byJavaImage(Image image);

}
