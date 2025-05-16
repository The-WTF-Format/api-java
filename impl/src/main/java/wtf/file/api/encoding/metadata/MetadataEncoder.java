package wtf.file.api.encoding.metadata;

import wtf.file.api.exception.WtfException;
import wtf.file.api.util.WriteBitStream;
import wtf.file.api.v1.impl.metadata.MetadataContainerImpl;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MetadataEncoder {
    public static void encode(MetadataContainerImpl metadata, WriteBitStream bitStream) throws WtfException {

        for (Map.Entry<String, String> entry : metadata.asMap().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            for (char c : key.toCharArray()) {
                bitStream.write((byte) (c & 0x7F));
            }

            bitStream.write((byte) 0x00);
            bitStream.padToByte();

            bitStream.write(value.getBytes(StandardCharsets.UTF_8));

            bitStream.write((byte) 0x00);
            bitStream.padToByte();
        }

        bitStream.write((byte) 0x00);
    }
}
