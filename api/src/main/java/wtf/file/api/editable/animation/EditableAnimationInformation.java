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
     *
     * @return
     * @throws NumberOutOfBoundsException
     */
    void setFrames(int frames) throws NumberOutOfBoundsException;

    /**
     *
     * @param framesPerSecond
     * @throws NumberOutOfBoundsException
     */
    void setFramesPerSecond(int framesPerSecond) throws NumberOutOfBoundsException;

    /**
     *
     * @param secondsPerFrame
     * @throws NumberOutOfBoundsException
     */
    void setSecondsPerFrame(int secondsPerFrame) throws NumberOutOfBoundsException;

    @Override
    EditableFrame frame(int index);

}
