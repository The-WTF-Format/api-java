package wtf.file.api.v1.impl.editable.metadata;

import wtf.file.api.editable.metadata.EditableMetadataContainer;
import wtf.file.api.exception.IllegalCharacterException;
import wtf.file.api.v1.impl.metadata.MetadataContainerImpl;

import java.util.HashMap;
import java.util.Map;

public class EditableMetadataContainerImpl extends MetadataContainerImpl implements EditableMetadataContainer {

    public EditableMetadataContainerImpl(Map<String, String> metadata) {
        super(new HashMap<>(metadata));
    }

    @Override
    public void remove(String key) {
        this.metadata.remove(key);
    }

    @Override
    public void set(String key, String value) throws IllegalCharacterException {
        if (key.contains("\0")) {
            throw new IllegalCharacterException('\0', "ASCII-Characters without 0x00");
        }

        if (value.contains("\0")) {
            throw new IllegalCharacterException('\0', "UTF-8-Characters without 0x00");
        }

        this.metadata.put(key, value);
    }

}
