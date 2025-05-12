package wtf.file.api.v1.encoding.data;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.util.WriteBitStream;
import wtf.file.api.v1.decoding.data.ImageData;
import wtf.file.api.v1.impl.editable.EditableWtfImageImpl;

public class ImageDataEncoder {

    @NotNull
    public static ImageData asImageData(EditableWtfImageImpl image) {
        throw new NotYetImplementedException();
    }

    public static void encode(ImageData data, WriteBitStream bitStream) {
        throw new NotYetImplementedException();
    }

}
