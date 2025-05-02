package wtf.file.api;

import wtf.file.api.builder.WtfImageBuilder;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.exception.WtfException;

import java.io.IOException;
import java.nio.file.Path;

public class WtfLoader {

    public WtfImage from(Path path) throws WtfException, IOException {
        throw new NotYetImplementedException("Loading from path");
    }

    public WtfImageBuilder by() {
        return new WtfImageBuilder();
    }

}
