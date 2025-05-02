package wtf.file.api.editable.metadata;

import wtf.file.api.exception.IllegalCharacterException;
import wtf.file.api.metadata.MetadataContainer;

public interface EditableMetadataContainer extends MetadataContainer {

    /**
     *
     * @param key
     * @throws IllegalCharacterException
     */
    void remove(String key) throws IllegalCharacterException;

    /**
     *
     * @param key
     * @param value
     * @throws IllegalCharacterException
     */
    void set(String key, String value) throws IllegalCharacterException;

}
