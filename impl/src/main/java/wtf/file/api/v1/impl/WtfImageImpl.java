package wtf.file.api.v1.impl;

import wtf.file.api.WtfImage;
import wtf.file.api.animation.AnimationInformation;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.data.Pixel;
import wtf.file.api.editable.EditableWtfImage;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.metadata.MetadataContainer;
import wtf.file.api.version.Version;

import java.awt.*;

public class WtfImageImpl implements WtfImage {

    @Override
    public Version version() {
        return null;
    }

    @Override
    public int width() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public ColorSpace colorSpace() {
        return null;
    }

    @Override
    public short channelWidth() {
        return 0;
    }

    @Override
    public AnimationInformation animationInformation() {
        return null;
    }

    @Override
    public MetadataContainer metadataContainer() {
        return null;
    }

    @Override
    public EditableWtfImage edit() {
        return null;
    }

    @Override
    public Pixel[][] pixels() {
        return new Pixel[0][];
    }

    @Override
    public Pixel at(int x, int y) throws NumberOutOfBoundsException {
        return null;
    }

    @Override
    public Image asJavaImage() {
        return null;
    }

}
