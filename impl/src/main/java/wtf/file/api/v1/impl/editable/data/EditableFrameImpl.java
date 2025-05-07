package wtf.file.api.v1.impl.editable.data;

import wtf.file.api.editable.data.EditableFrame;
import wtf.file.api.editable.data.EditablePixel;
import wtf.file.api.exception.NumberOutOfBoundsException;

import java.awt.*;

public class EditableFrameImpl implements EditableFrame {

    @Override
    public EditablePixel[][] pixels() {
        return new EditablePixel[0][];
    }

    @Override
    public EditablePixel at(int x, int y) throws NumberOutOfBoundsException {
        return null;
    }

    @Override
    public Image asJavaImage() {
        return null;
    }

    @Override
    public void byJavaImage(Image image) {

    }

}
