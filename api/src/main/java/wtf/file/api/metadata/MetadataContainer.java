package wtf.file.api.metadata;

import java.util.List;
import java.util.Map;

public interface MetadataContainer {

    boolean has(String key);

    List<String> keys();

    String get(String key);

    Map<String, String> asMap();

}
