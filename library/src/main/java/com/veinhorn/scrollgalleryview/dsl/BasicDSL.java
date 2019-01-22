package com.veinhorn.scrollgalleryview.dsl;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.R;
import com.veinhorn.scrollgalleryview.loader.DefaultVideoLoader;

public class BasicDSL {
    public static MediaInfo video(String url) {
        return MediaInfo.mediaLoader(new DefaultVideoLoader(url, R.mipmap.default_video));
    }
}
