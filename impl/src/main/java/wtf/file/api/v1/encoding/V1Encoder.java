package wtf.file.api.v1.encoding;

import wtf.file.api.encoding.VersionEncoder;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.WriteBitStream;
import wtf.file.api.v1.pixel.BitSize;
import wtf.file.api.v1.pixel.ImageData;
import wtf.file.api.v1.encoding.clut.ClutEncoder;
import wtf.file.api.v1.encoding.data.ImageDataEncoder;
import wtf.file.api.v1.encoding.header.HeaderEncoder;
import wtf.file.api.v1.encoding.metadata.MetadataEncoder;
import wtf.file.api.v1.impl.editable.EditableWtfImageImpl;
import wtf.file.api.v1.impl.editable.metadata.EditableMetadataContainerImpl;
import wtf.file.api.version.Version;

public class V1Encoder {

    public static byte[] encode(EditableWtfImageImpl image) throws WtfException {
        WriteBitStream bitStream = new WriteBitStream();

        VersionEncoder.encode(Version.VERSION_1, bitStream);

        ImageData imageData = ImageDataEncoder.asImageData(image);
        HeaderEncoder.encode(image, bitStream);
        int length = ClutEncoder.encode(imageData, bitStream);
        MetadataEncoder.encode((EditableMetadataContainerImpl) image.metadataContainer(), bitStream);
        ImageDataEncoder.encode(image.colorSpace(), BitSize.of(
            image.channelWidth(), image.colorSpace(),
            length,
            image.animationInformation().frames(), image.height(), image.width()
        ), imageData, bitStream);

        return bitStream.getBytes();
    }

}
