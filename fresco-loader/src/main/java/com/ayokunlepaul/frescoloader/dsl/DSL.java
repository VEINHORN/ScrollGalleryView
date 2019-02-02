package com.ayokunlepaul.frescoloader.dsl;

import com.ayokunlepaul.frescoloader.FrescoMediaHelper;
import com.veinhorn.scrollgalleryview.MediaInfo;

import java.util.List;

public class DSL {
    private static FrescoMediaHelper mediaHelper = new FrescoMediaHelper();

    public static MediaInfo image(String url) {
        return mediaHelper.image(url);
    }

    public static List<MediaInfo> images(List<String> urls) {
        return mediaHelper.images(urls);
    }

    public static List<MediaInfo> images(String... urls) {
        return mediaHelper.images(urls);
    }

    public static MediaInfo video(String url, int placeholderViewId) {
        return mediaHelper.video(url, placeholderViewId);
    }
}
