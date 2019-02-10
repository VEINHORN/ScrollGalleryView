package com.veinhorn.scrollgalleryview.loader.picasso;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.builder.BasicMediaHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PicassoMediaHelper extends BasicMediaHelper {
    @Override
    public MediaInfo image(String url) {
        return mediaInfo(url, null);
    }

    @Override
    public MediaInfo image(String url, String description) {
        return mediaInfo(url, description);
    }

    @Override
    public List<MediaInfo> images(List<String> urls) {
        List<MediaInfo> medias = new ArrayList<>();

        for (String url : urls) {
            medias.add(mediaInfo(url, null));
        }

        return medias;
    }

    @Override
    public List<MediaInfo> images(String... urls) {
        return images(Arrays.asList(urls));
    }

    // TODO: Add null checking for image url
    private MediaInfo mediaInfo(String url, String description) {
        if (description != null) {
            return MediaInfo.mediaLoader(new PicassoImageLoader(url), description);
        }
        return MediaInfo.mediaLoader(new PicassoImageLoader(url));
    }
}
