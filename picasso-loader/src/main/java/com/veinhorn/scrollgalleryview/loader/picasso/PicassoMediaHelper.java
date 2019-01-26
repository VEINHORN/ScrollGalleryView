package com.veinhorn.scrollgalleryview.loader.picasso;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.builder.BasicMediaHelper;

public class PicassoMediaHelper extends BasicMediaHelper {
    @Override
    public MediaInfo image(String url) {
        return MediaInfo.mediaLoader(new PicassoImageLoader(url));
    }
}
