package wtf.file.api.v1.impl.metadata;

import wtf.file.api.metadata.MetadataContainer;

import java.util.List;
import java.util.Map;

public class MetadataContainerImpl implements MetadataContainer {

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
