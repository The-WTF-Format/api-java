package wtf.file.api.v1.impl.animation;

import wtf.file.api.animation.AnimationInformation;
import wtf.file.api.data.Frame;

public class AnimationInformationImpl implements AnimationInformation  {

    @Override
    public int frames() {
        return 0;
    }

    @Override
    public boolean isFpsCoded() {
        return false;
    }

    @Override
    public int framesPerSecond() {
        return 0;
    }

    @Override
    public int secondsPerFrame() {
        return 0;
    }

    @Override
    public Frame frame(int index) {
        return null;
    }

}
