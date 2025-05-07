package wtf.file.api.v1.decoding.metadata;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.util.Pair;

import java.util.Map;

public class MetadataDecoder {

    @NotNull
    public static Pair<Map<String, String>, byte[]> decode(byte[] bytes) {
        throw new NotYetImplementedException();
    }

}
