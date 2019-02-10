package com.veinhorn.scrollgalleryview.loader.picasso.dsl;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.loader.picasso.PicassoMediaHelper;

import java.util.List;

public class DSL {
    private static PicassoMediaHelper mediaHelper = new PicassoMediaHelper();

    public static MediaInfo image(String url) {
        return mediaHelper.image(url);
    }

    public static MediaInfo image(String url, String description) {
        return mediaHelper.image(url, description);
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
