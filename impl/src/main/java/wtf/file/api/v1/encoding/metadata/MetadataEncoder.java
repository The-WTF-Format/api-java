package wtf.file.api.v1.encoding.metadata;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.v1.impl.editable.metadata.EditableMetadataContainerImpl;

public class MetadataEncoder {

    @NotNull
    public static byte[] encode(EditableMetadataContainerImpl metadata) {
        throw new NotYetImplementedException();
    }

}
