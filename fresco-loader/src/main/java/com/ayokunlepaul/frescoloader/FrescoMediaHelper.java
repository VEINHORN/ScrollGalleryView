package com.ayokunlepaul.frescoloader;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.builder.BasicMediaHelper;

public class FrescoMediaHelper extends BasicMediaHelper {
    @Override
    public MediaInfo image(String url) {
        return MediaInfo.mediaLoader(new FrescoImageLoader(url));
    }
}
