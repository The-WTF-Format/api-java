package wtf.file.api.v1.impl.editable.data;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.editable.data.EditablePixel;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.util.NumberUtil;
import wtf.file.api.v1.impl.data.PixelImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EditablePixelImpl extends PixelImpl implements EditablePixel {

    private final Map<ColorChannel, Short> values;

    public EditablePixelImpl(Map<ColorChannel, Short> values, ColorSpace colorSpace, int channelWidth) {
        super(values, colorSpace, channelWidth);
        this.values = new HashMap<>(values);
    }

    @Override
    public Map<ColorChannel, Short> values() {
        return Collections.unmodifiableMap(this.values);
    }

    @Override
    public void setValues(Map<ColorChannel, Short> values) throws NumberOutOfBoundsException {
        if (!values.keySet().equals(new HashSet<>(this.colorSpace().channels()))) {
            throw new IllegalArgumentException("The values do not match the color space.");
        }

        int maxValue = NumberUtil.getMaxValue(this.channelWidth);
        values.forEach((channel, value) ->
            NumberUtil.checkBounds(value, 0, maxValue, "value for channel " + channel));

        this.values.clear();
        this.values.putAll(values);
    }

    @Override
    public void setValue(ColorChannel channel, short value) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(value, 0, NumberUtil.getMaxValue(this.channelWidth), "value");
        if (!this.colorSpace().channels().contains(channel)) {
            throw new IllegalArgumentException("The color space does not contain the channel " + channel + ".");
        }

        this.values.put(channel, value);
    }

}
