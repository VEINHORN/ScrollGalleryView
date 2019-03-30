package ogbe.ozioma.com.glideimageloader.dsl;

import com.veinhorn.scrollgalleryview.MediaInfo;

import java.util.List;

import ogbe.ozioma.com.glideimageloader.GlideMediaHelper;

public class DSL {
    private static GlideMediaHelper mediaHelper = new GlideMediaHelper();

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
