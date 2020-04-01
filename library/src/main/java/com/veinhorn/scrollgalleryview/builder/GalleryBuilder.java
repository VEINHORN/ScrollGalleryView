package com.veinhorn.scrollgalleryview.builder;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;

import java.util.List;

/**
 * GalleryBuilder simplifies ScrollGalleryView initialization process
 */
public interface GalleryBuilder {
    /**
     * Sets up settings for gallery
     * @param settings contains options for gallery
     * @return GalleryBuilder object
     */
    GalleryBuilder settings(GallerySettings settings);

    GalleryBuilder onImageClickListener(ScrollGalleryView.OnImageClickListener listener);

    GalleryBuilder onImageLongClickListener(ScrollGalleryView.OnImageLongClickListener listener);

    /**
     * Adds single MediaInfo to gallery
     * @param media is a single MediaInfo object
     * @return GalleryBuilder object
     */
    GalleryBuilder add(MediaInfo media);

    /**
     * Adds a list of MediaInfos to gallery
     * @param medias is a list of MediaInfo objects
     * @return GalleryBuilder object
     */
    GalleryBuilder add(List<MediaInfo> medias);

    /**
     * Builds gallery from provided medias
     * @return initialized ScrollGalleryView
     */
    ScrollGalleryView build();
}
