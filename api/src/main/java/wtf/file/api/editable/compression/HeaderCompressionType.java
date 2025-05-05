package wtf.file.api.editable.compression;

/**
 * Enum representing the types of compression applied specifically to image headers
 * during save operations.
 * <p>
 * The available header compression types are:
 * - NO_COMPRESSION: No compression is applied.
 * - RUN_LENGTH_ENCODING: Run-length encoding is applied to compress every data after the image signature.
 * <p>
 * These compression types can be used in conjunction with data compression settings
 * to customize the behavior of image saving operations.
 */
public enum HeaderCompressionType {

    /**
     * Represents a header compression type where no compression is applied.
     * Data in the image is stored exactly as is, without any modifications or reduction in size.
     */
    NO_COMPRESSION,

    /**
     * Represents a header compression type where run-length encoding is applied to the image data.
     */
    RUN_LENGTH_ENCODING,

}
