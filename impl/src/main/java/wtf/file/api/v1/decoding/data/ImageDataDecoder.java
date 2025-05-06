package wtf.file.api.v1.decoding.data;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.util.Pair;
import wtf.file.api.v1.decoding.clut.ClutInformation;
import wtf.file.api.v1.decoding.header.HeaderInformation;

public class ImageDataDecoder {

    @NotNull
    public static Pair<ImageData, byte[]> decode(HeaderInformation headerInformation, ClutInformation clutInformation, byte[] bytes) {
        throw new NotYetImplementedException();
    }

}
