package wtf.file.api.editable.compression;

public enum DataCompressionType {

    NO_COMPRESSION(false, false),
    INLINE_COMPRESSION(false, true),
    MAPPED_COMPRESSION(true, false),
    COMBINED_COMPRESSION(true, true);

    private final boolean useHuffman;
    private final boolean useReference;


    DataCompressionType(boolean useHuffman, boolean useReference) {
        this.useHuffman = useHuffman;
        this.useReference = useReference;
    }

    public boolean useHuffman() {
        return useHuffman;
    }

    public boolean useReference() {
        return useReference;
    }

}
