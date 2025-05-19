package wtf.file.api.v1.impl.editable;

import wtf.file.api.animation.AnimationInformation;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.editable.EditableWtfImage;
import wtf.file.api.editable.animation.EditableAnimationInformation;
import wtf.file.api.editable.compression.DataCompressionType;
import wtf.file.api.editable.data.EditablePixel;
import wtf.file.api.editable.metadata.EditableMetadataContainer;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.exception.WtfException;
import wtf.file.api.metadata.MetadataContainer;
import wtf.file.api.util.NumberUtil;
import wtf.file.api.v1.decoding.header.HeaderInformation;
import wtf.file.api.v1.encoding.V1Encoder;
import wtf.file.api.v1.impl.animation.AnimationInformationImpl;
import wtf.file.api.v1.impl.editable.animation.EditableAnimationInformationImpl;
import wtf.file.api.v1.impl.editable.metadata.EditableMetadataContainerImpl;
import wtf.file.api.version.Version;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EditableWtfImageImpl implements EditableWtfImage {

    private int width;
    private int height;
    private ColorSpace colorSpace;
    private int channelWidth;
    private final EditableAnimationInformation animationInformation;
    private final EditableMetadataContainer metadataContainer;

    public EditableWtfImageImpl(int width, int height, ColorSpace colorSpace, int channelWidth, AnimationInformation animationInformation, MetadataContainer metadataContainer) {
        this.width = width;
        this.height = height;
        this.colorSpace = colorSpace;
        this.channelWidth = channelWidth;
        this.animationInformation = new EditableAnimationInformationImpl(
            animationInformation.frames(),
            animationInformation.isFpsCoded() ? HeaderInformation.FrameCoding.FPS_CODED : HeaderInformation.FrameCoding.SPF_CODED,
            animationInformation.isFpsCoded() ? animationInformation.framesPerSecond() : animationInformation.secondsPerFrame(),
            colorSpace, channelWidth,
            ((AnimationInformationImpl) animationInformation).data()
        );
        this.metadataContainer = new EditableMetadataContainerImpl(metadataContainer.asMap());
    }

    @Override
    public void setWidth(int width) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(width, 1, 65535, "width");

        ((EditableAnimationInformationImpl) this.animationInformation()).scaleWidth(width);
        this.width = width;
    }

    @Override
    public void setHeight(int height) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(height, 1, 65535, "height");

        ((EditableAnimationInformationImpl) this.animationInformation()).scaleHeight(height);
        this.height = height;
    }

    @Override
    public void setColorSpace(ColorSpace colorSpace) {
        ((EditableAnimationInformationImpl) this.animationInformation()).changeColorSpace(colorSpace);
        this.colorSpace = colorSpace;
    }

    @Override
    public void setChannelWidth(int channelWidth) throws NumberOutOfBoundsException {
        NumberUtil.checkBounds(channelWidth, 1, 16, "channelWidth");

        ((EditableAnimationInformationImpl) this.animationInformation()).scaleChannelWidth(channelWidth);
        this.channelWidth = channelWidth;
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
    public EditableAnimationInformation animationInformation() {
        return this.animationInformation;
    }

    @Override
    public EditableMetadataContainer metadataContainer() {
        return this.metadataContainer;
    }

    @Override
    public void save(Path path, DataCompressionType dataCompressionType) throws IOException, WtfException {
        byte[] bytes = V1Encoder.encode(this, dataCompressionType);

        Path absolutePath = path.toAbsolutePath();
        if (absolutePath.getParent() != null && !Files.exists(absolutePath.getParent())) {
            Files.createDirectories(absolutePath.getParent());
        }

        Files.write(absolutePath, bytes);
    }

    @Override
    public EditablePixel[][] pixels() {
        return this.animationInformation().frame(0).pixels();
    }

    @Override
    public EditablePixel at(int x, int y) throws NumberOutOfBoundsException {
        return this.animationInformation().frame(0).at(x, y);
    }

    @Override
    public Image asJavaImage() {
        return this.animationInformation().frame(0).asJavaImage();
    }

    @Override
    public void byJavaImage(BufferedImage image) {
        this.animationInformation().frame(0).byJavaImage(image);
    }

}
