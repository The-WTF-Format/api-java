package wtf.file.api.v1.decoding.metadata;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.util.ReadBitStream;

import java.util.Map;

public class MetadataDecoder {

    @NotNull
    public static Map<String, String> decode(ReadBitStream bitStream) {
        throw new NotYetImplementedException();
    }

}
