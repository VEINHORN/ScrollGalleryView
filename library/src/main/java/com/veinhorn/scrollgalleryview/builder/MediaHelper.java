package com.veinhorn.scrollgalleryview.builder;

import com.veinhorn.scrollgalleryview.MediaInfo;

import java.util.List;

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
     * Creates a list of MediaInfos from the list of image urls
     * @param urls is a list of image urls
     * @return a list of MediaInfo objects
     */
    List<MediaInfo> images(List<String> urls);

    /**
     * Creates a list of MediaInfos from the array of image urls
     * @param urls is an array of image urls
     * @return a list of MediaInfo objects
     */
    List<MediaInfo> images(String... urls);

    /**
     * Creates MediaInfo object based on image url
     * @param url is an image URL address
     * @param placeholderViewId is an image resource id which is used as video placeholder
     * @return MediaInfo object
     */
    MediaInfo video(String url, int placeholderViewId);
}
