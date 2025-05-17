package wtf.file.api.v1.decoding.metadata;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.ReadBitStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MetadataDecoder {

    @NotNull
    public static Map<String, String> decode(ReadBitStream bitStream) throws WtfException {
        Map<String, String> metadata = new HashMap<>();
        byte[] nulChar = {(byte) 0x00};

        String key;
        String value;

        while(bitStream.hasRemaining(8) && !Arrays.equals(bitStream.peekBytes(1), nulChar)) {
            key = bitStream.readAscii();

            if (key.length() == 0) {
                break;
            }

            bitStream.skipBytePadding();

            value = bitStream.readUtf8();

            metadata.put(key, value);
        }

        return metadata;
    }

}
