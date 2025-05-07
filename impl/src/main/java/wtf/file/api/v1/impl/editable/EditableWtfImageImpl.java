package wtf.file.api.v1.impl.editable;

import wtf.file.api.color.ColorSpace;
import wtf.file.api.editable.EditableWtfImage;
import wtf.file.api.editable.animation.EditableAnimationInformation;
import wtf.file.api.editable.compression.DataCompressionType;
import wtf.file.api.editable.data.EditablePixel;
import wtf.file.api.editable.metadata.EditableMetadataContainer;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.version.Version;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;

public class EditableWtfImageImpl implements EditableWtfImage {

    @Override
    public void setWidth(int width) throws NumberOutOfBoundsException {

    }

    @Override
    public void setHeight(int height) throws NumberOutOfBoundsException {

    }

    @Override
    public void setColorSpace(ColorSpace colorSpace) {

    }

    @Override
    public void setChannelWidth(int channelWidth) throws NumberOutOfBoundsException {

    }

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
    public int channelWidth() {
        return 0;
    }

    @Override
    public EditableAnimationInformation animationInformation() {
        return null;
    }

    @Override
    public EditableMetadataContainer metadataContainer() {
        return null;
    }

    @Override
    public EditableWtfImage edit() {
        return null;
    }

    @Override
    public void save(Path path, DataCompressionType dataCompressionType) throws IOException {

    }

    @Override
    public EditablePixel[][] pixels() {
        return new EditablePixel[0][];
    }

    @Override
    public EditablePixel at(int x, int y) throws NumberOutOfBoundsException {
        return null;
    }

    @Override
    public Image asJavaImage() {
        return null;
    }

    @Override
    public void byJavaImage(Image image) {

    }

}
