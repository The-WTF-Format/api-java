package wtf.file.api.v1.impl.animation;

import wtf.file.api.animation.AnimationInformation;
import wtf.file.api.data.Frame;
import wtf.file.api.data.Pixel;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.util.NumberUtil;
import wtf.file.api.v1.decoding.header.HeaderInformation;
import wtf.file.api.v1.impl.data.FrameImpl;

import java.util.HashMap;
import java.util.Map;

public class AnimationInformationImpl implements AnimationInformation  {

    private final int frames;
    private final HeaderInformation.FrameCoding frameCoding;
    private final int timingValue;
    private final Pixel[][][] pixels;
    private final Map<Integer, Frame> frameStore = new HashMap<>();

    public AnimationInformationImpl(HeaderInformation headerInformation, Pixel[][][] pixels) {
        this.frames = headerInformation.frames();
        this.frameCoding = headerInformation.frameTiming();
        this.timingValue = headerInformation.frameTimingValue();
        this.pixels = pixels;
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
        return isFpsCoded() ? this.timingValue : 0;
    }

    @Override
    public int secondsPerFrame() {
        return isFpsCoded() ? 0 : this.timingValue;
    }

    @Override
    public Frame frame(int index) {
        NumberUtil.checkBounds(index, 0, this.frames - 1, "index");

        frameStore.computeIfAbsent(index, i -> new FrameImpl(this.pixels[i]));
        return frameStore.get(index);
    }

}
