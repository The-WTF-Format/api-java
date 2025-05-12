package wtf.file.api.v1.encoding.header;

import wtf.file.api.exception.WtfException;
import wtf.file.api.util.WriteBitStream;
import wtf.file.api.v1.decoding.header.HeaderInformation;
import wtf.file.api.v1.impl.editable.EditableWtfImageImpl;
import wtf.file.api.v1.util.header.ColorSpaceUtil;

public class HeaderEncoder {

    public static void encode(EditableWtfImageImpl image, WriteBitStream bitStream) throws WtfException {
        bitStream.writeNumber(image.height(), 16);
        bitStream.writeNumber(image.width(), 16);

        bitStream.write(ColorSpaceUtil.reverseColorSpaceMap().get(image.colorSpace()));
        bitStream.writeNumber(image.channelWidth(), 8);

        HeaderInformation.FrameCoding frameCoding = image.animationInformation().isFpsCoded()
                ? HeaderInformation.FrameCoding.FPS_CODED
                : HeaderInformation.FrameCoding.SPF_CODED;
        bitStream.write(frameCoding.flag(), 1);

        int timingValue = image.animationInformation().isFpsCoded() ?
                image.animationInformation().framesPerSecond()
                : image.animationInformation().secondsPerFrame();
        bitStream.writeNumber(timingValue, 7);
    }

}
