package com.veinhorn.example;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;

public class MainActivity extends FragmentActivity {

    private ScrollGalleryView scrollGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = convertDrawableToBitmap(R.drawable.wallpaper7);

        scrollGalleryView = (ScrollGalleryView) findViewById(R.id.scroll_gallery_view);
        scrollGalleryView
                .setThumbnailSize(100)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager())
                .addMedia(MediaInfo.imageLoader(R.drawable.wallpaper1))
                .addMedia(MediaInfo.imageLoader(bitmap))
                .addMedia(MediaInfo.mediaLoader(new MediaInfo.MediaLoader() {
                    @Override
                    public Bitmap loadBitmap(Activity activity) {
                        return convertDrawableToBitmap(R.drawable.wallpaper3);
                    }
                }))
                .addMedia(MediaInfo
                        .url("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4"));

    }

    private Bitmap convertDrawableToBitmap(int image) {
        return ((BitmapDrawable) getResources().getDrawable(image)).getBitmap();
    }
}
