package wtf.file.api;

import wtf.file.api.animation.AnimationInformation;
import wtf.file.api.color.ColorSpace;
import wtf.file.api.data.Frame;
import wtf.file.api.editable.EditableWtfImage;
import wtf.file.api.metadata.MetadataContainer;
import wtf.file.api.version.Version;

public interface WtfImage extends Frame {

    Version version();

    int width();

    int height();

    ColorSpace colorSpace();

    short channelWidth();

    AnimationInformation animationInformation();

    MetadataContainer metadataContainer();

    EditableWtfImage edit();

}
