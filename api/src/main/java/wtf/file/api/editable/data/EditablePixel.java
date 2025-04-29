package wtf.file.api.editable.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.NumberOutOfBoundsException;

import java.util.Map;

public interface EditablePixel extends Pixel {

    ColorSpace colorSpace();

    /**
     *
     * @param values
     * @throws NumberOutOfBoundsException
     */
    void setValues(Map<ColorChannel, Long> values);

    /**
     *
     * @param channel
     * @return
     * @throws NumberOutOfBoundsException
     */
    void setValue(ColorChannel channel, long value);

}
