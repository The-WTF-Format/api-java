package wtf.file.api;

import wtf.file.api.builder.WtfImageBuilder;
import wtf.file.api.decoding.WtfDecoder;
import wtf.file.api.exception.WtfException;
import wtf.file.api.impl.WtfImageBuilderImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The WtfLoader class is used to handle the creation and loading of WtfImage objects.
 * It provides methods to load images from a file path and to initiate the building of new images.
 */
public class WtfLoader {

    /**
     * Creates a WtfImage instance by loading it from the specified path.
     *
     * @param path the path to load the WtfImage from
     * @return the loaded WtfImage instance
     * @throws WtfException if there is an issue specific to WtfImage loading.
     *                      e.g., an unknown version or too few or many bytes
     * @throws IOException if an I/O error occurs during the loading process
     * @see WtfImage
     */
    public WtfImage from(Path path) throws WtfException, IOException {
        byte[] bytes = Files.readAllBytes(path);
        return WtfDecoder.decode(bytes);
    }

    /**
     * Initiates the building process for a new instance of WtfImage using the WtfImageBuilder.
     *
     * @return a new instance of WtfImageBuilder, which provides methods to configure and build a WtfImage.
     * @see WtfImageBuilder
     */
    public WtfImageBuilder by() {
        return new WtfImageBuilderImpl();
    }

}
