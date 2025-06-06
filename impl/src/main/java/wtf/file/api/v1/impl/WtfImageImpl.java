package wtf.file.api.v1.impl;

import wtf.file.api.WtfImage;
import wtf.file.api.animation.AnimationInformation;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.data.Pixel;
import wtf.file.api.editable.EditableWtfImage;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.metadata.MetadataContainer;
import wtf.file.api.v1.decoding.header.HeaderInformation;
import wtf.file.api.v1.impl.animation.AnimationInformationImpl;
import wtf.file.api.v1.impl.editable.EditableWtfImageImpl;
import wtf.file.api.v1.impl.metadata.MetadataContainerImpl;
import wtf.file.api.version.Version;

import java.awt.*;
import java.util.Map;

public class WtfImageImpl implements WtfImage {

    private final int width;
    private final int height;
    private final ColorSpace colorSpace;
    private final int channelWidth;
    private final AnimationInformation animationInformation;
    private final MetadataContainer metadataContainer;

    public WtfImageImpl(HeaderInformation headerInformation, Map<String, String> metadata, Pixel[][][] pixels) {
        this.width = headerInformation.width();
        this.height = headerInformation.height();
        this.colorSpace = headerInformation.colorSpace();
        this.channelWidth = headerInformation.channelWidth();
        this.animationInformation = new AnimationInformationImpl(headerInformation, pixels);
        this.metadataContainer = new MetadataContainerImpl(metadata);
    }

    @Override
    public Version version() {
        return Version.VERSION_1;
    }

    @Override
    public int width() {
        return this.width;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public ColorSpace colorSpace() {
        return this.colorSpace;
    }

    @Override
    public int channelWidth() {
        return this.channelWidth;
    }

    @Override
    public AnimationInformation animationInformation() {
        return this.animationInformation;
    }

    @Override
    public MetadataContainer metadataContainer() {
        return this.metadataContainer;
    }

    @Override
    public EditableWtfImage edit() {
        return new EditableWtfImageImpl(
            this.width, this.height,
            this.colorSpace, this.channelWidth,
            this.animationInformation,
            this.metadataContainer
        );
    }

    @Override
    public Pixel[][] pixels() {
        return this.animationInformation().frame(0).pixels();
    }

    @Override
    public Pixel at(int x, int y) throws NumberOutOfBoundsException {
        return this.animationInformation().frame(0).at(x, y);
    }

    @Override
    public Image asJavaImage() {
        return this.animationInformation.frame(0).asJavaImage();
    }

}
