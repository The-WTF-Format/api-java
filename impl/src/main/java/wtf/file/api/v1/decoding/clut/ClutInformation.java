package wtf.file.api.v1.decoding.clut;

import wtf.file.api.color.channel.ColorChannel;

import java.util.Map;

public record ClutInformation(
        int codeLength,
        Map<Long, Map<ColorChannel, Short>> clut
) {

}
