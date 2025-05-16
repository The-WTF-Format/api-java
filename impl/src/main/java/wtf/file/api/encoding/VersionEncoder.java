package wtf.file.api.encoding;

import wtf.file.api.exception.WtfException;
import wtf.file.api.util.WriteBitStream;
import wtf.file.api.version.Version;

public class VersionEncoder {

    public static void encode(Version version, WriteBitStream bitStream) throws WtfException {
        byte versionByte = switch (version) {
            case Version.VERSION_1 -> 1;
            default -> throw new WtfException("Unknown version: " + version);
        };
        bitStream.write(versionByte);
    }
}
