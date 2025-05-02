package wtf.file.api.builder;

import wtf.file.api.WtfImage;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.exception.NumberOutOfBoundsException;
import wtf.file.api.exception.ValueNotSetException;
import wtf.file.api.version.Version;

public class WtfImageBuilder {

    public WtfImageBuilder version(Version version) {
        throw new NotYetImplementedException("Setting version");
    }

    public WtfImageBuilder width(int width) throws NumberOutOfBoundsException  {
        throw new NotYetImplementedException("Setting width");
    }

    public WtfImageBuilder height(int height) throws NumberOutOfBoundsException {
        throw new NotYetImplementedException("Setting height");
    }

    public WtfImageBuilder colorSpace(ColorSpace colorSpace) {
        throw new NotYetImplementedException("Setting color space");
    }

    public WtfImageBuilder channelWidth(short channelWidth) throws NumberOutOfBoundsException {
        throw new NotYetImplementedException("Setting channel width");
    }

    public WtfImageBuilder frames(int frames) throws NumberOutOfBoundsException {
        throw new NotYetImplementedException("Setting frames");
    }

    public WtfImageBuilder framesPerSecond(int fps) throws NumberOutOfBoundsException {
        throw new NotYetImplementedException("Setting frames per second");
    }

    public WtfImageBuilder secondsPerFrame(int spf) throws NumberOutOfBoundsException {
        throw new NotYetImplementedException("Setting seconds per frame");
    }

    public WtfImage build() throws ValueNotSetException {
        throw new NotYetImplementedException("Building WtfImage");
    }

}
