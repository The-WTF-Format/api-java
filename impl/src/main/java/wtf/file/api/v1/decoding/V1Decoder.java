package wtf.file.api.v1.decoding;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.WtfImage;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.ReadBitStream;
import wtf.file.api.v1.decoding.clut.ClutDecoder;
import wtf.file.api.v1.decoding.data.ImageDataDecoder;
import wtf.file.api.v1.decoding.header.HeaderDecoder;
import wtf.file.api.v1.decoding.metadata.MetadataDecoder;
import wtf.file.api.v1.impl.WtfImageImpl;

public class V1Decoder {

    @NotNull
    public static WtfImage decode(ReadBitStream bitStream) throws WtfException {
        var headerInformation = HeaderDecoder.decode(bitStream);
        var clutInformation = ClutDecoder.decode(headerInformation, bitStream);
        var metadataInformation = MetadataDecoder.decode(bitStream);
        var imageData = ImageDataDecoder.decode(headerInformation, clutInformation, bitStream);
        var dereferencedImageData = imageData.dereference(headerInformation.colorSpace(), headerInformation.channelWidth(), clutInformation);

        return new WtfImageImpl(headerInformation, dereferencedImageData);
    }

}
