package wtf.file.api.v1.impl.metadata;

import wtf.file.api.metadata.MetadataContainer;

import java.util.List;
import java.util.Map;

public class MetadataContainerImpl implements MetadataContainer {

    protected final Map<String, String> metadata;

    public MetadataContainerImpl(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean has(String key) {
        return metadata.containsKey(key);
    }

    @Override
    public List<String> keys() {
        return List.of();
    }

    @Override
    public String get(String key) {
        return metadata.getOrDefault(key, null);
    }

    @Override
    public Map<String, String> asMap() {
        return Map.copyOf(metadata);
    }

}
