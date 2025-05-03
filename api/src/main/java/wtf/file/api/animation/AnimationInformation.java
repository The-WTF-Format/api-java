package wtf.file.api.animation;

import wtf.file.api.data.Frame;
import wtf.file.api.exception.NumberOutOfBoundsException;

/**
 * Represents metadata and behavior for an animated object, including frame data
 * and timing information.
 */
public interface AnimationInformation {

    /**
     * Returns the total number of frames in the animation.
     *
     * @return the number of frames
     */
    int frames();

    /**
     * Checks whether the object is animated, defined by having more than one frame.
     *
     * @return {@code true} if the animation has more than one frame, {@code false} otherwise
     */
    default boolean isAnimated() {
        return frames() > 1;
    }

    /**
     * Indicates whether the animation timing is defined using frames per second (FPS).
     *
     * @return {@code true} if FPS is used, {@code false} otherwise
     */
    boolean isFpsCoded();

    /**
     * Indicates whether the animation timing is defined using seconds per frame (SPF).
     * This is the inverse of {@link #isFpsCoded()}.
     *
     * @return {@code true} if SPF is used, {@code false} otherwise
     */
    default boolean isSpfCoded() {
        return !isFpsCoded();
    }

    /**
     * Returns the number of frames per second, if timing is FPS-coded.
     *
     * @return the frames per second value
     */
    int framesPerSecond();

    /**
     * Returns the number of seconds per frame, if timing is SPF-coded.
     *
     * @return the seconds per frame value
     */
    int secondsPerFrame();

    /**
     * Retrieves a specific frame by index.
     *
     * @param index the zero-based index of the frame to retrieve
     * @return the {@link Frame} at the specified index
     * @throws NumberOutOfBoundsException if the index is out of range
     */
    Frame frame(int index);

}
