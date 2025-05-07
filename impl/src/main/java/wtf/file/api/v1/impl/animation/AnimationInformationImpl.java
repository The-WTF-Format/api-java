package wtf.file.api.v1.impl.animation;

import wtf.file.api.animation.AnimationInformation;
import wtf.file.api.data.Frame;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.v1.decoding.header.HeaderInformation;

public class AnimationInformationImpl implements AnimationInformation  {

    private final int frames;
    private final HeaderInformation.FrameCoding frameCoding;
    private final int timingValue;

    public AnimationInformationImpl(HeaderInformation headerInformation) {
        this.frames = headerInformation.frames();
        this.frameCoding = headerInformation.frameTiming();
        this.timingValue = headerInformation.frameTimingValue();
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
        throw new NotYetImplementedException();
    }

}
