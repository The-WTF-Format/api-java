package wtf.file.api.v1.pixel;

import wtf.file.api.data.Pixel;
import wtf.file.api.v1.decoding.clut.ClutInformation;

public interface PixelInformation {

    Pixel pixel(ClutInformation clutInformation);

}
