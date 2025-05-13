package wtf.file.api.v1.encoding.clut;

import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.util.WriteBitStream;
import wtf.file.api.v1.pixel.ImageData;

public class ClutEncoder {

    /**
     * Returns the number of bits for a clut code.
     * @param imageData the image data to encode
     * @param bitStream the bit stream to write to
     * @return the number of bits written
     */
    public static int encode(ImageData imageData, WriteBitStream bitStream) {
        throw new NotYetImplementedException();
    }

}
