package com.veinhorn.scrollgalleryview.builder;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;

public interface GalleryBuilder {
    GalleryBuilder settings(GallerySettings settings);
    GalleryBuilder add(MediaInfo mediaInfo);
    ScrollGalleryView build();
}
