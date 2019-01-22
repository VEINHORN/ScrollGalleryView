package com.veinhorn.scrollgalleryview.loader.picasso.dsl;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.dsl.BasicDSL;
import com.veinhorn.scrollgalleryview.loader.picasso.PicassoMediaHelper;

public class DSL extends BasicDSL {
    private static PicassoMediaHelper mediaHelper = new PicassoMediaHelper();

    public static MediaInfo media(String url) {
        return mediaHelper.media(url);
    }
}
