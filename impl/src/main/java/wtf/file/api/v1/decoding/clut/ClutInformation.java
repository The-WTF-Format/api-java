package wtf.file.api.v1.decoding.clut;

import wtf.file.api.color.channel.ColorChannel;

import java.util.Map;

public record ClutInformation(
        byte codeLength,
        Map<Integer, Map<ColorChannel, Integer>> clut
) {

}
