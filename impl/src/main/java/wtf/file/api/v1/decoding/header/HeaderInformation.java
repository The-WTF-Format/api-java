package wtf.file.api.v1.decoding.header;

import wtf.file.api.color.ColorSpace;

public record HeaderInformation(
        int height, int width,
        ColorSpace colorSpace, int channelWidth,
        int frames,
        FrameCoding frameTiming, int frameTimingValue
) {

    public enum FrameCoding {

        FPS_CODED((byte) 0x00),
        SPF_CODED((byte) 0x01);

        private final byte flag;

        FrameCoding(byte flag) {
            this.flag = flag;
        }

        public byte flag() {
            return flag;
        }

        public static FrameCoding fromTFlag(boolean tFlag) {
            return tFlag ? FPS_CODED : SPF_CODED;
        }

    }

}
