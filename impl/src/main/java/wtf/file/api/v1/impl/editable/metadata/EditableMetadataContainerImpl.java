package wtf.file.api.v1.impl.editable.metadata;

import wtf.file.api.editable.metadata.EditableMetadataContainer;
import wtf.file.api.exception.IllegalCharacterException;

import java.util.List;
import java.util.Map;

public class EditableMetadataContainerImpl implements EditableMetadataContainer {

    @Override
    public void remove(String key) {

    }

    @Override
    public void set(String key, String value) throws IllegalCharacterException {

    }

    @Override
    public boolean has(String key) {
        return false;
    }

    @Override
    public List<String> keys() {
        return List.of();
    }

    @Override
    public String get(String key) {
        return "";
    }

    @Override
    public Map<String, String> asMap() {
        return Map.of();
    }

}
