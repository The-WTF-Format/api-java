package wtf.file.api.decoding;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.WtfImage;
import wtf.file.api.decoding.version.VersionDecoder;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.ReadBitStream;
import wtf.file.api.v1.decoding.V1Decoder;

public class WtfDecoder {

    @NotNull
    public static WtfImage decode(byte[] bytes) throws WtfException {
        ReadBitStream bitStream = new ReadBitStream(bytes);
        var version = VersionDecoder.decode(bitStream);

        return switch (version) {
            case VERSION_1 -> V1Decoder.decode(bitStream);
        };
    }

}
