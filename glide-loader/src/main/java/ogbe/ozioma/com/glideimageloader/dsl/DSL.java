package ogbe.ozioma.com.glideimageloader.dsl;

import com.veinhorn.scrollgalleryview.MediaInfo;

import ogbe.ozioma.com.glideimageloader.GlideMediaHelper;

public class DSL {
    private static GlideMediaHelper mediaHelper = new GlideMediaHelper();

    public static MediaInfo image(String url) {
        return mediaHelper.image(url);
    }

    public static MediaInfo video(String url, int placeholderViewId) {
        return mediaHelper.video(url, placeholderViewId);
    }
}
