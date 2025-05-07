package wtf.file.api.v1.impl.editable.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;
import wtf.file.api.editable.data.EditablePixel;
import wtf.file.api.exception.NumberOutOfBoundsException;

import java.util.Map;

public class EditablePixelImpl implements EditablePixel {

    @Override
    public ColorSpace colorSpace() {
        return null;
    }

    @Override
    public Map<ColorChannel, Short> values() {
        return Map.of();
    }

    @Override
    public short valueOf(ColorChannel channel) {
        return 0;
    }

    @Override
    public Pixel as(ColorSpace colorSpace) {
        return null;
    }

    @Override
    public void setValues(Map<ColorChannel, Short> values) throws NumberOutOfBoundsException {

    }

    @Override
    public void setValue(ColorChannel channel, short value) throws NumberOutOfBoundsException {

    }

}
