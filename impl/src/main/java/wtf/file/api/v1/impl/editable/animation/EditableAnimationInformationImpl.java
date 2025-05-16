package wtf.file.api.v1.impl.editable.animation;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.color.channel.ColorChannel;
import wtf.file.api.data.Pixel;
import wtf.file.api.editable.animation.EditableAnimationInformation;
import wtf.file.api.editable.data.EditableFrame;
import wtf.file.api.editable.data.EditablePixel;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.util.ColorUtil;
import wtf.file.api.util.NumberUtil;
import wtf.file.api.v1.decoding.header.HeaderInformation;
import wtf.file.api.v1.impl.editable.data.EditableFrameImpl;
import wtf.file.api.v1.impl.editable.data.EditablePixelImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EditableAnimationInformationImpl implements EditableAnimationInformation {

    private int frames;
    private HeaderInformation.FrameCoding frameCoding;
    private int timingValue;
    private ColorSpace colorSpace;
    private int channelWidth;
    private EditablePixel[][][] pixels;
    private final Map<Integer, EditableFrame> frameStore = new HashMap<>();

    public EditableAnimationInformationImpl(int frames, HeaderInformation.FrameCoding frameCoding, int timingValue, ColorSpace colorSpace, int channelWidth, Pixel[][][] pixels) {
        this.frames = frames;
        this.frameCoding = frameCoding;
        this.timingValue = timingValue;
        this.colorSpace = colorSpace;
        this.channelWidth = channelWidth;
        this.pixels = Arrays.stream(pixels)
            .map(frame -> Arrays.stream(frame)
                .map(row -> Arrays.stream(row)
                    .map(pixel -> new EditablePixelImpl(pixel.values(), colorSpace, channelWidth))
                    .toArray(EditablePixel[]::new))
                .toArray(EditablePixel[][]::new))
            .toArray(EditablePixel[][][]::new);
    }

    @Override
    public void setFrames(int frames) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(frames, 1, 255, "frames");

        if (frames == this.frames) return;

        int delta = frames - this.frames;
        this.pixels = Arrays.copyOf(pixels, frames);

        if (delta > 0) {
            Map<ColorChannel, Short> defaultValues = ColorUtil.getDefaultColors(colorSpace, channelWidth);

            for (int i = 0; i < delta; i++) {
                int frame = this.frames + i;
                for (int x = 0; x < this.pixels[frame].length; x++) {
                    for (int y = 0; y < this.pixels[frame][x].length; y++) {
                        this.pixels[frame][x][y] = new EditablePixelImpl(defaultValues, colorSpace, channelWidth);
                    }
                }
            }
        } else {
            for (int i = 0; i > delta; i--) {
                int frame = this.frames + i;
                this.frameStore.remove(frame);
            }
        }

        this.frames = frames;
    }

    @Override
    public void setFramesPerSecond(int framesPerSecond) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(framesPerSecond, 1, 127, "framesPerSecond");

        this.frameCoding = HeaderInformation.FrameCoding.FPS_CODED;
        this.timingValue = framesPerSecond;
    }

    @Override
    public void setSecondsPerFrame(int secondsPerFrame) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(secondsPerFrame, 1, 127, "secondsPerFrame");

        this.frameCoding = HeaderInformation.FrameCoding.SPF_CODED;
        this.timingValue = secondsPerFrame;
    }

    @Override
    public int frames() {
        return this.frames;
    }

    @Override
    public boolean isFpsCoded() {
        return this.frameCoding == HeaderInformation.FrameCoding.FPS_CODED;
    }

    @Override
    public int framesPerSecond() {
        return this.frameCoding == HeaderInformation.FrameCoding.FPS_CODED ? this.timingValue : 0;
    }

    @Override
    public int secondsPerFrame() {
        return this.frameCoding == HeaderInformation.FrameCoding.SPF_CODED ? this.timingValue : 0;
    }

    @Override
    public EditableFrame frame(int index) {
        NumberUtil.checkBounds(index, 0, this.frames - 1, "index");

        frameStore.computeIfAbsent(index, i -> new EditableFrameImpl(this.pixels[i], colorSpace, channelWidth));
        return frameStore.get(index);
    }

    public void scaleWidth(int width) {
        frameStore.clear();

        int delta = width - this.pixels[0].length;

        for (int frame = 0; frame < this.pixels.length; frame++) {
            this.pixels[frame] = Arrays.copyOf(this.pixels[frame], width);

            if (delta > 0) {
                for (int i = 0; i < delta; i++) {
                    int x = this.pixels[frame].length + delta;
                    for (int y = 0; y < this.pixels[frame][x].length; y++) {
                        this.pixels[frame][x][y] = new EditablePixelImpl(
                            ColorUtil.getDefaultColors(colorSpace, channelWidth),
                            colorSpace, channelWidth
                        );
                    }
                }
            }
        }
    }

    public void scaleHeight(int height) {
        frameStore.clear();

        int delta = height - this.pixels[0][0].length;

        for (int frame = 0; frame < this.pixels.length; frame++) {
            for (int x = 0; x < this.pixels[frame].length; x++) {
                this.pixels[frame][x] = Arrays.copyOf(this.pixels[frame][x], height);

                if (delta > 0) {
                    for (int i = 0; i < delta; i++) {
                        int y = this.pixels[frame][x].length + delta;
                        this.pixels[frame][x][y] = new EditablePixelImpl(
                            ColorUtil.getDefaultColors(colorSpace, channelWidth),
                            colorSpace, channelWidth
                        );
                    }
                }
            }
        }
    }

    public void changeColorSpace(ColorSpace colorSpace) {
        for (int frame = 0; frame < this.pixels.length; frame++) {
            for (int x = 0; x < this.pixels[frame].length; x++) {
                for (int y = 0; y < this.pixels[frame][x].length; y++) {
                    this.pixels[frame][x][y] = new EditablePixelImpl(
                        ColorUtil.toColorSpace(this.pixels[frame][x][y].values(), this.colorSpace, colorSpace, this.channelWidth),
                        colorSpace, this.channelWidth
                    );
                }
            }
        }

        this.colorSpace = colorSpace;
    }

    public void scaleChannelWidth(int channelWidth) {
        for (int frame = 0; frame < this.pixels.length; frame++) {
            for (int x = 0; x < this.pixels[frame].length; x++) {
                for (int y = 0; y < this.pixels[frame][x].length; y++) {
                    this.pixels[frame][x][y] = new EditablePixelImpl(
                        ColorUtil.toChannelWidth(this.pixels[frame][x][y].values(), this.channelWidth, channelWidth),
                        this.colorSpace, channelWidth
                    );
                }
            }
        }

        this.channelWidth = channelWidth;
    }
}
