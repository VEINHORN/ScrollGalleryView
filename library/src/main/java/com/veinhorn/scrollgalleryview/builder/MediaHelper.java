package com.veinhorn.scrollgalleryview.builder;

import com.veinhorn.scrollgalleryview.MediaInfo;

/**
 * MediaHelper is a specification which is used for building DSLs. It includes a bunch of methods
 * which help to simplify adding media to your gallery
 */
public interface MediaHelper {
    /**
     * Creates MediaInfo object based on image url
     * @param url is an image URL address
     * @return MediaInfo object
     */
    MediaInfo image(String url);

    /**
     * Creates MediaInfo object based on image url
     * @param url is an image URL address
     * @param placeholderViewId is an image resource id which is used as video placeholder
     * @return MediaInfo object
     */
    MediaInfo video(String url, int placeholderViewId);
}
