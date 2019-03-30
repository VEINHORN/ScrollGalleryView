package ogbe.ozioma.com.glideimageloader;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.builder.BasicMediaHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GlideMediaHelper extends BasicMediaHelper {
    @Override
    public MediaInfo image(String url) {
        return MediaInfo.mediaLoader(new GlideImageLoader(url));
    }

    @Override
    public MediaInfo image(String url, String description) {
        return mediaInfo(url, description);
    }

    @Override
    public List<MediaInfo> images(List<String> urls) {
        List<MediaInfo> medias = new ArrayList<>();

        for (String url : urls) {
            medias.add(mediaInfo(url));
        }

        return medias;
    }

    @Override
    public List<MediaInfo> images(String... urls) {
        return images(Arrays.asList(urls));
    }

    private MediaInfo mediaInfo(String url) {
        return mediaInfo(url, null);
    }

    private MediaInfo mediaInfo(String url, String description) {
        if (description != null) {
            return MediaInfo.mediaLoader(new GlideImageLoader(url), description);
        }
        return MediaInfo.mediaLoader(new GlideImageLoader(url));
    }
}
