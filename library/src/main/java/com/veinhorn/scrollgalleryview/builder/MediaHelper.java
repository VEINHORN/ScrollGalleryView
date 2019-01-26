package com.veinhorn.scrollgalleryview.builder;

import com.veinhorn.scrollgalleryview.MediaInfo;

/**
 * MediaHelper provides a bunch of methods which simplify adding media to gallery
 */
public interface MediaHelper {
    /**
     * Create MediaInfo based on image url
     * @param url
     * @return
     */
    MediaInfo image(String url);

    /**
     * Create MediaInfo based on image url
     * @param url
     * @param placeholderViewId
     * @return
     */
    MediaInfo video(String url, int placeholderViewId);
}
