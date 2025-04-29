package wtf.file.api.editable;

import wtf.file.api.WtfImage;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.editable.animation.EditableAnimationInformation;
import wtf.file.api.editable.compression.CompressionType;
import wtf.file.api.editable.data.EditableFrame;
import wtf.file.api.editable.metadata.EditableMetadataContainer;
import wtf.file.api.exception.NumberOutOfBoundsException;

import java.io.IOException;
import java.nio.file.Path;

public interface EditableWtfImage extends WtfImage, EditableFrame {

    /**
     *
     * @param width
     * @throws NumberOutOfBoundsException
     */
    void setWidth(int width);

    /**
     *
     * @param height
     * @throws NumberOutOfBoundsException
     */
    void setHeight(int height);

    void setColorSpace(ColorSpace colorSpace);

    /**
     *
     * @param channelWidth
     * @throws NumberOutOfBoundsException
     */
    void setChannelWidth(short channelWidth);

    @Override
    EditableAnimationInformation animationInformation();

    @Override
    EditableMetadataContainer metadataContainer();

    void save(Path path, CompressionType compressionType) throws IOException;

    default void save(Path path) throws IOException {
        save(path, CompressionType.MAPPED_COMPRESSION);
    }

}
