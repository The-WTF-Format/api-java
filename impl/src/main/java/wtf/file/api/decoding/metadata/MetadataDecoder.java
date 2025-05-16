package wtf.file.api.decoding.metadata;

import wtf.file.api.exception.WtfException;
import wtf.file.api.util.ReadBitStream;
import wtf.file.api.v1.impl.metadata.MetadataContainerImpl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MetadataDecoder {

    public static MetadataContainerImpl decode(ReadBitStream bitStream) throws WtfException {
        Map<String, String> metadata = new HashMap<>();

        byte[] nulChar = {0x00};

        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();

        while(bitStream.hasRemaining(8)) {
            while (!Arrays.equals(bitStream.peekBytes(1), nulChar)) {
                key.append(bitStream.readAscii());
            }

            if (key.isEmpty()) {
                throw new WtfException("Metadata key is empty");
            }

            bitStream.skipBytePadding();

            while (bitStream.peekBytes(8) != nulChar) {
                value.append(bitStream.hasRemaining(1) ? bitStream.readUtf8() : "");
            }

            metadata.put(key.toString(), value.toString());
        }

        return new MetadataContainerImpl(metadata);
    }
}
