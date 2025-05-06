package wtf.file.api.decoding;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.WtfImage;
import wtf.file.api.decoding.version.VersionDecoder;
import wtf.file.api.v1.decoding.V1Decoder;

public class WtfDecoder {

    @NotNull
    public static WtfImage decode(byte[] bytes) {
        var version = VersionDecoder.decode(bytes);

        return switch (version.first()) {
            case VERSION_1 -> V1Decoder.decode(version.second());
        };
    }

}
