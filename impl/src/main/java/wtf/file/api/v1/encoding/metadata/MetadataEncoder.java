package wtf.file.api.v1.encoding.metadata;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.util.WriteBitStream;
import wtf.file.api.v1.impl.editable.metadata.EditableMetadataContainerImpl;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MetadataEncoder {

    public static void encode(EditableMetadataContainerImpl metadata, WriteBitStream bitStream) {
        for (Map.Entry<String, String> entry : metadata.asMap().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            bitStream.writeAscii(key);

            bitStream.write((byte) 0x00);
            bitStream.padToByte();

            bitStream.write(value.getBytes(StandardCharsets.UTF_8));

            bitStream.writeUtf8(value);
        }

        bitStream.write((byte) 0x00);
    }
}
