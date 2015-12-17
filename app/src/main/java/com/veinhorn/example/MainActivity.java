package com.veinhorn.example;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;
import com.veinhorn.scrollgalleryview.loader.DefaultImageLoader;
import com.veinhorn.scrollgalleryview.loader.DefaultVideoLoader;
import com.veinhorn.scrollgalleryview.loader.MediaLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private static final ArrayList<String> images = new ArrayList<>(Arrays.asList(
            "http://img1.goodfon.ru/original/1920x1080/d/f5/aircraft-jet-su-47-berkut.jpg",
            "http://www.dishmodels.ru/picture/glr/13/13312/g13312_7657277.jpg",
            "http://img2.goodfon.ru/original/1920x1080/b/c9/su-47-berkut-c-37-firkin.jpg",
            "http://www.avsimrus.com/file_images/15/img4951_1.jpg",
            "http://www.avsimrus.com/file_images/15/img4951_3.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/0/07/Sukhoi_Su-47_in_2008.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/b/b4/Sukhoi_Su-47_Berkut_%28S-37%29_in_2001.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37333-4.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37-0.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37-6.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37-9.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37-2.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37-1.jpg"
    ));
    private ScrollGalleryView scrollGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = convertDrawableToBitmap(R.drawable.wallpaper7);

        List<MediaInfo> infos = new ArrayList<>(images.size());
        for (String url : images) {
            infos.add(MediaInfo.mediaLoader(new PicassoImageLoader(url)));
        }

        scrollGalleryView = (ScrollGalleryView) findViewById(R.id.scroll_gallery_view);
        scrollGalleryView
                .setThumbnailSize(100)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager())
                .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.drawable.wallpaper1)))
                .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(bitmap)))
                .addMedia(MediaInfo.mediaLoader(new MediaLoader() {
                    @Override
                    public boolean isImage() {
                        return true;
                    }

                    @Override
                    public void loadMedia(Context context, ImageView imageView, MediaLoader.SuccessCallback callback) {
                        Bitmap bitmap = convertDrawableToBitmap(R.drawable.wallpaper3);
                        imageView.setImageBitmap(bitmap);
                        callback.onSuccess();
                    }

                    @Override
                    public void loadThumbnail(Context context, ImageView thumbnailView, MediaLoader.SuccessCallback callback) {
                        Bitmap bitmap = convertDrawableToBitmap(R.drawable.wallpaper3);
                        thumbnailView.setImageBitmap(bitmap);
                        callback.onSuccess();
                    }
                }))
                .addMedia(MediaInfo.mediaLoader(
                        new DefaultVideoLoader("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4", R.mipmap.default_video)))
                .addMedia(infos);
    }

    private Bitmap convertDrawableToBitmap(int image) {
        return ((BitmapDrawable) getResources().getDrawable(image)).getBitmap();
    }
}
