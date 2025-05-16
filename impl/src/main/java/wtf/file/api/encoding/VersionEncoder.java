package wtf.file.api.encoding;

import wtf.file.api.exception.WtfException;
import wtf.file.api.util.WriteBitStream;
import wtf.file.api.version.Version;

public class VersionEncoder {

    public static void encode(Version version, WriteBitStream bitStream) throws WtfException {
        byte versionByte;
        if (version == Version.VERSION_1) {
            versionByte = 1;
        } else {
            throw new WtfException("Unknown version: " + version);
        }
        bitStream.write(versionByte);
    }
}
