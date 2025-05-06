package wtf.file.api.editable.animation;

import wtf.file.api.animation.AnimationInformation;
import wtf.file.api.editable.data.EditableFrame;
import wtf.file.api.exception.NumberOutOfBoundsException;

/**
 * An interface extending {@link AnimationInformation} to provide editable functionality for animation data.
 * <p>
 * This interface allows modifying animation properties such as the number of frames, frames per second, and
 * seconds per frame, along with fetching editable frames. It also ensures that any modifications to these properties
 * are validated and within the allowed range.
 * </p>
 */
public interface EditableAnimationInformation extends AnimationInformation  {

    /**
     * Sets the number of frames.
     *
     * @param frames the number of frames to set
     * @throws NumberOutOfBoundsException if the specified number of frames is out of the valid range
     */
    void setFrames(int frames) throws NumberOutOfBoundsException;

    /**
     * Sets the frame rate in frames per second (FPS).
     * This method is used when the timing is FPS-coded.
     *
     * @param framesPerSecond the number of frames per second to set
     * @throws NumberOutOfBoundsException if the specified FPS value is out of the valid range
     */
    void setFramesPerSecond(int framesPerSecond) throws NumberOutOfBoundsException;

    /**
     * Sets the duration of a frame in seconds.
     * This method is used when the timing is SPF-coded.
     *
     * @param secondsPerFrame the duration of a single frame in seconds
     * @throws NumberOutOfBoundsException if the specified SPF value is out of the valid range
     */
    void setSecondsPerFrame(int secondsPerFrame) throws NumberOutOfBoundsException;

    @Override
    EditableFrame frame(int index);

}
