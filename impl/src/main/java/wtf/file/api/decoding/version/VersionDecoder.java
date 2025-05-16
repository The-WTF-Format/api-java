package wtf.file.api.decoding.version;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.ReadBitStream;
import wtf.file.api.version.Version;

public class VersionDecoder {

    @NotNull
    public static Version decode(ReadBitStream bitStream) throws WtfException {
        int versionByte = bitStream.readByte();
        if (versionByte == 0x01) {
            return Version.VERSION_1;
        } else {
            throw new WtfException("Unknown version code: " + versionByte);
        }
    }
}
