package wtf.file.api.v1.encoding.data;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.v1.decoding.data.ImageData;
import wtf.file.api.v1.impl.editable.EditableWtfImageImpl;

public class ImageDataEncoder {

    @NotNull
    public static ImageData asImageData(EditableWtfImageImpl image) {
        throw new NotYetImplementedException();
    }

    @NotNull
    public static byte[] encode(ImageData data) {
        throw new NotYetImplementedException();
    }

}
