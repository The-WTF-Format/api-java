package wtf.file.api.v1.encoding.header;

import com.google.common.primitives.Ints;
import wtf.file.api.exception.WtfException;
import wtf.file.api.util.ByteArrayUtil;
import wtf.file.api.v1.impl.editable.EditableWtfImageImpl;
import wtf.file.api.v1.util.header.ColorSpaceUtil;

public class HeaderEncoder {

    public static byte[] encode(EditableWtfImageImpl image) throws WtfException {
        byte[] headerBytes = new byte[8];

        byte[] heightBytes = Ints.toByteArray(image.height());
        if (!ByteArrayUtil.checkEmptyUpperBytes(heightBytes, 2)) {
            throw new WtfException(String.format("Height must not be bigger than 65535, got %d", image.height()));
        }
        System.arraycopy(heightBytes, 2, headerBytes, 0, 2);

        byte[] widthBytes = Ints.toByteArray(image.width());
        if (!ByteArrayUtil.checkEmptyUpperBytes(widthBytes, 2)) {
            throw new WtfException(String.format("Width must not be bigger than 65535, got %d", image.width()));
        }
        System.arraycopy(widthBytes, 2, headerBytes, 2, 2);

        Byte colorSpace = ColorSpaceUtil.reverseColorSpaceMap().get(image.colorSpace());
        if (colorSpace == null) {
            throw new WtfException(String.format("Unknown color space %s", image.colorSpace()));
        }
        headerBytes[4] = colorSpace;

        byte[] channelWidthBytes = Ints.toByteArray(image.channelWidth());
        if (!ByteArrayUtil.checkEmptyUpperBytes(channelWidthBytes, 3)) {
            throw new WtfException(String.format("Channel width must not be bigger than 255, got %d", image.channelWidth()));
        }
        System.arraycopy(channelWidthBytes, 3, headerBytes, 5, 1);
        
        byte[] frameBytes = Ints.toByteArray(image.animationInformation().frames());
        if (!ByteArrayUtil.checkEmptyUpperBytes(frameBytes, 3)) {
            throw new WtfException(String.format("Frames must not be bigger than 255, got %d", image.animationInformation().frames()));
        }
        System.arraycopy(frameBytes, 3, headerBytes, 6, 1);

        int timingValue = image.animationInformation().isFpsCoded() ? image.animationInformation().framesPerSecond() : image.animationInformation().secondsPerFrame();

        byte[] timingValueBytes = Ints.toByteArray(timingValue);
        if (!ByteArrayUtil.checkEmptyUpperBytes(timingValueBytes, 3)) {
            throw new WtfException(String.format("Animation timing value must not be bigger than 127, got %d", image.animationInformation().isFpsCoded() ? image.animationInformation().framesPerSecond() : image.animationInformation().secondsPerFrame()));
        }
        if ((timingValueBytes[3] & 0x80) != 0) {
            throw new WtfException(String.format("Animation timing value must not be bigger than 127, got %d", image.animationInformation().isFpsCoded() ? image.animationInformation().framesPerSecond() : image.animationInformation().secondsPerFrame()));
        }

        byte timingByte = (byte) (timingValueBytes[3] | (image.animationInformation().isFpsCoded() ? 0x00 : 0x80));
        headerBytes[7] = timingByte;

        return headerBytes;
    }

}
