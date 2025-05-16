package wtf.file.api.impl;

import wtf.file.api.WtfImage;
import wtf.file.api.builder.WtfImageBuilder;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.exception.ValueNotSetException;
import wtf.file.api.util.ColorUtil;
import wtf.file.api.util.NumberUtil;
import wtf.file.api.v1.decoding.header.HeaderInformation;
import wtf.file.api.v1.impl.WtfImageImpl;
import wtf.file.api.v1.impl.data.PixelImpl;
import wtf.file.api.version.Version;

import java.util.Arrays;

public class WtfImageBuilderImpl implements WtfImageBuilder {

    private Version version = Version.VERSION_1;
    private int width;
    private int height;
    private ColorSpace colorSpace;
    private short channelWidth = 8;
    private int frames = 1;
    private HeaderInformation.FrameCoding frameCoding;
    private int frameTimingValue = 0;

    @Override
    public WtfImageBuilder version(Version version) {
        this.version = version;
        return this;
    }

    @Override
    public WtfImageBuilder width(int width) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(width, 1, 65535, "width");
        this.width = width;
        return this;
    }

    @Override
    public WtfImageBuilder height(int height) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(height, 1, 65535, "height");
        this.height = height;
        return this;
    }

    @Override
    public WtfImageBuilder colorSpace(ColorSpace colorSpace) {
        this.colorSpace = colorSpace;
        return this;
    }

    @Override
    public WtfImageBuilder channelWidth(short channelWidth) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(channelWidth, 1, 16, "channelWidth");
        this.channelWidth = channelWidth;
        return this;
    }

    @Override
    public WtfImageBuilder frames(int frames) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(frames, 1, 255, "frames");
        this.frames = frames;
        return this;
    }

    @Override
    public WtfImageBuilder framesPerSecond(int fps) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(fps, 1, 127, "fps");
        this.frameCoding = HeaderInformation.FrameCoding.FPS_CODED;
        this.frameTimingValue = fps;
        return this;
    }

    @Override
    public WtfImageBuilder secondsPerFrame(int spf) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(spf, 1, 127, "spf");
        this.frameCoding = HeaderInformation.FrameCoding.SPF_CODED;
        this.frameTimingValue = spf;
        return this;
    }

    @Override
    public WtfImage build() throws ValueNotSetException {
        return switch (version) {
            case VERSION_1 -> {
                if (this.height == 0) {
                    throw new ValueNotSetException("height");
                }

                if (this.width == 0) {
                    throw new ValueNotSetException("width");
                }

                if (this.colorSpace == null) {
                    throw new ValueNotSetException("colorSpace");
                }

                Pixel pixel = new PixelImpl(
                    ColorUtil.getDefaultColors(colorSpace, channelWidth),
                    this.colorSpace,
                    this.channelWidth);

                Pixel[][][] pixels = new Pixel[this.frames][this.width][this.height];
                Arrays.stream(pixels).forEach(row -> Arrays.stream(row).forEach(col -> Arrays.fill(col, pixel)));

                yield new WtfImageImpl(
                    new HeaderInformation(
                        this.height, this.width,
                        this.colorSpace, this.channelWidth,
                        this.frames, this.frameCoding, this.frameTimingValue
                    ),
                    pixels
                );
            }
        };

    }
}
