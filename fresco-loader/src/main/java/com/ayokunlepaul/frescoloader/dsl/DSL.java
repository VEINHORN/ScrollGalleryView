package com.ayokunlepaul.frescoloader.dsl;

import com.ayokunlepaul.frescoloader.FrescoMediaHelper;
import com.veinhorn.scrollgalleryview.MediaInfo;

public class DSL {
    private static FrescoMediaHelper mediaHelper = new FrescoMediaHelper();

    public static MediaInfo image(String url) {
        return mediaHelper.image(url);
    }

    public static MediaInfo video(String url, int placeholderViewId) {
        return mediaHelper.video(url, placeholderViewId);
    }
}
