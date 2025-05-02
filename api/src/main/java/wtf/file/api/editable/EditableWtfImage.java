package wtf.file.api.editable;

import wtf.file.api.WtfImage;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.editable.animation.EditableAnimationInformation;
import wtf.file.api.editable.compression.DataCompressionType;
import wtf.file.api.editable.compression.HeaderCompressionType;
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
    void setWidth(int width) throws NumberOutOfBoundsException;

    /**
     *
     * @param height
     * @throws NumberOutOfBoundsException
     */
    void setHeight(int height) throws NumberOutOfBoundsException;

    void setColorSpace(ColorSpace colorSpace);

    /**
     *
     * @param channelWidth
     * @throws NumberOutOfBoundsException
     */
    void setChannelWidth(short channelWidth) throws NumberOutOfBoundsException;

    @Override
    EditableAnimationInformation animationInformation();

    @Override
    EditableMetadataContainer metadataContainer();

    void save(Path path, HeaderCompressionType headerCompressionType, DataCompressionType dataCompressionType) throws IOException;

    default void save(Path path, HeaderCompressionType headerCompressionType) throws IOException {
        save(path, headerCompressionType, DataCompressionType.MAPPED_COMPRESSION);
    }

    default void save(Path path, DataCompressionType dataCompressionType) throws IOException {
        save(path, HeaderCompressionType.RUN_LENGTH_ENCODING, dataCompressionType);
    }

    default void save(Path path) throws IOException {
        save(path, HeaderCompressionType.RUN_LENGTH_ENCODING, DataCompressionType.MAPPED_COMPRESSION);
    }

}
