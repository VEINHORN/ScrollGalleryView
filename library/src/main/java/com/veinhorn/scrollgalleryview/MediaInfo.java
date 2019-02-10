package com.veinhorn.scrollgalleryview;

import com.veinhorn.scrollgalleryview.loader.MediaLoader;

/**
 * MediaInfo contains the information required to load and display the media in the gallery.
 */
public class MediaInfo {
    private MediaLoader mLoader;
    private String description;

    private MediaInfo() {

    }

    private MediaInfo(MediaLoader mediaLoader, String description) {
        this.mLoader = mediaLoader;
        this.description = description;
    }

    /**
     * Creates MediaInfo object from MediaLoader
     * @param mediaLoader
     * @return MediaInfo object
     */
    public static MediaInfo mediaLoader(MediaLoader mediaLoader) {
        return new MediaInfo().setLoader(mediaLoader);
    }

    public static MediaInfo mediaLoader(MediaLoader mediaLoader, String description) {
        return new MediaInfo(mediaLoader, description);
    }

    public MediaLoader getLoader() {
        return mLoader;
    }

    public MediaInfo setLoader(MediaLoader loader) {
        mLoader = loader;
        return this;
    }

    public String getDescription() {
        return description;
    }
}
