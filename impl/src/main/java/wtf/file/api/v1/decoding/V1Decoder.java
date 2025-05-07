package wtf.file.api.v1.decoding;

import org.jetbrains.annotations.NotNull;
import wtf.file.api.WtfImage;
import wtf.file.api.exception.NotYetImplementedException;
import wtf.file.api.v1.decoding.clut.ClutDecoder;
import wtf.file.api.v1.decoding.data.ImageDataDecoder;
import wtf.file.api.v1.decoding.header.HeaderDecoder;
import wtf.file.api.v1.decoding.metadata.MetadataDecoder;

public class V1Decoder {

    @NotNull
    public static WtfImage decode(byte[] bytes) {
        var headerPair = HeaderDecoder.decode(bytes);
        var headerInformation = headerPair.first();

        var clutPair = ClutDecoder.decode(headerPair.second());
        var clutInformation = clutPair.first();

        var metadataPair = MetadataDecoder.decode(clutPair.second());
        var metadataInformation = metadataPair.first();

        var imageDataPair = ImageDataDecoder.decode(headerInformation, clutInformation, metadataPair.second());
        var imageData = imageDataPair.first();

        throw new NotYetImplementedException();
    }

}
