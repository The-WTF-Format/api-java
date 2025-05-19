package wtf.file.api.decoding.version;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.ReadBitStream;
import wtf.file.api.version.Version;

public class VersionDecoder {

    @NotNull
    public static Version decode(ReadBitStream bitStream) throws WtfException {
        int versionByte = bitStream.readByte();
        return switch (versionByte) {
            case 0x01 -> Version.VERSION_1;
            default -> throw new WtfException("Unknown version code: " + versionByte);
        };
    }
}
