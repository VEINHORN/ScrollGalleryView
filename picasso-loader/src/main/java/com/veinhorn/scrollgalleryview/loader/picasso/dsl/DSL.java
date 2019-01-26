package com.veinhorn.scrollgalleryview.loader.picasso.dsl;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.loader.picasso.PicassoMediaHelper;

public class DSL {
    private static PicassoMediaHelper mediaHelper = new PicassoMediaHelper();

    public static MediaInfo image(String url) {
        return mediaHelper.image(url);
    }

    public static MediaInfo video(String url, int placeholderViewId) {
        return mediaHelper.video(url, placeholderViewId);
    }
}
