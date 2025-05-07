package wtf.file.api.v1.impl.editable.animation;

import wtf.file.api.editable.animation.EditableAnimationInformation;
import wtf.file.api.editable.data.EditableFrame;
import wtf.file.api.exception.NumberOutOfBoundsException;

public class EditableAnimationInformationImpl implements EditableAnimationInformation {

    @Override
    public void setFrames(int frames) throws NumberOutOfBoundsException {

    }

    @Override
    public void setFramesPerSecond(int framesPerSecond) throws NumberOutOfBoundsException {

    }

    @Override
    public void setSecondsPerFrame(int secondsPerFrame) throws NumberOutOfBoundsException {

    }

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
    public EditableFrame frame(int index) {
        return null;
    }

}
