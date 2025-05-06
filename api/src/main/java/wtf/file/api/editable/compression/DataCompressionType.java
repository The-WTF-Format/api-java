package wtf.file.api.editable.compression;

/**
 * Enum representing the types of data compression available for use in image saving operations.
 * Each compression type determines whether specific compression techniques,
 * such as Huffman coding or Reference-based compression, are used.
 * <p>
 * The following data compression types are available:
 * - NO_COMPRESSION: No data compression is applied.
 * - INLINE_COMPRESSION: Only reference-based compression is applied.
 * - MAPPED_COMPRESSION: Only Huffman-based compression is applied.
 * - COMBINED_COMPRESSION: Both Huffman and reference-based compression techniques are applied.
 * <p>
 * The compression type can be used to optimize data storage and performance during
 * save operations and other data processing tasks.
 */
public enum DataCompressionType {

    /**
     * Represents a data compression type where no compression is applied.
     * Both Huffman-based and reference-based compression techniques are disabled.
     * This type is suitable when compression is not required or when raw data storage is preferred.
     */
    NO_COMPRESSION(false, false),

    /**
     * Represents the INLINE_COMPRESSION type, where only reference-based compression
     * techniques are applied.
     */
    INLINE_COMPRESSION(false, true),

    /**
     * Represents the MAPPED_COMPRESSION type, where only Huffman-based compression
     * techniques are applied.
     */
    MAPPED_COMPRESSION(true, false),

    /**
     * Represents the COMBINED_COMPRESSION type, where both Huffman-based and
     * reference-based compression techniques are applied.
     */
    COMBINED_COMPRESSION(true, true);

    private final boolean useHuffman;
    private final boolean useReference;


    DataCompressionType(boolean useHuffman, boolean useReference) {
        this.useHuffman = useHuffman;
        this.useReference = useReference;
    }

    /**
     * Indicates whether Huffman-based compression is enabled for this data compression type.
     *
     * @return true if Huffman-based compression is enabled, false otherwise
     */
    public boolean useHuffman() {
        return useHuffman;
    }

    /**
     * Indicates whether reference-based compression is enabled for this data compression type.
     *
     * @return true if reference-based compression is enabled; false otherwise
     */
    public boolean useReference() {
        return useReference;
    }

}
