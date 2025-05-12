package wtf.file.api.v1.decoding.data;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.util.ReadBitStream;
import wtf.file.api.v1.decoding.clut.ClutInformation;
import wtf.file.api.v1.decoding.header.HeaderInformation;

public class ImageDataDecoder {

    @NotNull
    public static ImageData decode(HeaderInformation headerInformation, ClutInformation clutInformation, ReadBitStream bitStream) {
        throw new NotYetImplementedException();
    }

}
