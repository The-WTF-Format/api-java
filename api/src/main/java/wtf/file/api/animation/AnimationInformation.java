package wtf.file.api.animation;

import wtf.file.api.data.Frame;

public interface AnimationInformation {

    int frames();

    default boolean isAnimated() {
        return frames() > 1;
    }

    boolean isFpsCoded();

    default boolean isSpfCoded() {
        return !isFpsCoded();
    }

    int framesPerSecond();

    int secondsPerFrame();

    Frame frame(int index);

}
