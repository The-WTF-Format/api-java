package wtf.file.api.decoder.animation;

public class AnimationDecoder {

    public static AnimationData decodeAnimationData(byte[] data) {
       return new AnimationData(0, false);
    }

    public static record AnimationData(int frames, boolean coded) {}

}
