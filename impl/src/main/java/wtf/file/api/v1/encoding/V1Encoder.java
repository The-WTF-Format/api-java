package wtf.file.api.v1.encoding;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.encoding.VersionEncoder;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.exception.WtfException;
import wtf.file.api.v1.decoding.data.ImageData;
import wtf.file.api.v1.encoding.clut.ClutEncoder;
import wtf.file.api.v1.encoding.data.ImageDataEncoder;
import wtf.file.api.v1.encoding.header.HeaderEncoder;
import wtf.file.api.v1.encoding.metadata.MetadataEncoder;
import wtf.file.api.v1.impl.editable.EditableWtfImageImpl;
import wtf.file.api.v1.impl.editable.metadata.EditableMetadataContainerImpl;
import wtf.file.api.version.Version;

public class V1Encoder {

    public static byte[] encode(EditableWtfImageImpl image) throws WtfException {
        byte[] versionBytes = VersionEncoder.encode(Version.VERSION_1);

        ImageData imageData = ImageDataEncoder.asImageData(image);
        byte[] headerBytes = HeaderEncoder.encode(image);
        byte[] clutBytes = ClutEncoder.encode(imageData);
        byte[] metadata = MetadataEncoder.encode((EditableMetadataContainerImpl) image.metadataContainer());
        byte[] imageDataBytes = ImageDataEncoder.encode(imageData);

        // TODO: Combine bytes

        throw new NotYetImplementedException();
    }

}
