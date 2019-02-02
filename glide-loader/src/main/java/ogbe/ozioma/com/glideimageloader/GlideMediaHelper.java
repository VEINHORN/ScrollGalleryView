package ogbe.ozioma.com.glideimageloader;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.builder.BasicMediaHelper;

public class GlideMediaHelper extends BasicMediaHelper {
    @Override
    public MediaInfo image(String url) {
        return MediaInfo.mediaLoader(new GlideImageLoader(url));
    }
}
