package com.veinhorn.scrollgalleryview.loader.picasso;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.builder.MediaHelper;

public class PicassoMediaHelper implements MediaHelper {
    @Override
    public MediaInfo media(String url) {
        return MediaInfo.mediaLoader(new PicassoImageLoader(url));
    }
}
