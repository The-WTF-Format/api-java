package wtf.file.api.editable.animation;

import wtf.file.api.animation.AnimationInformation;
import wtf.file.api.editable.data.EditableFrame;
import wtf.file.api.exception.NumberOutOfBoundsException;

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
