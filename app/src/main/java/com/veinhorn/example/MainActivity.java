package com.veinhorn.example;

import android.app.Activity;
import android.os.Bundle;

import com.veinhorn.scrollgalleryview.ScrollGalleryView;

public class MainActivity extends Activity {
    private ScrollGalleryView scrollGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollGalleryView = (ScrollGalleryView)findViewById(R.id.scroll_gallery_view);
        scrollGalleryView
                .setThumbnailSize(150)
                .addImage(R.drawable.wallpaper1)
                .addImage(R.drawable.wallpaper2)
                .addImage(R.drawable.wallpaper3)
                //.addImage(R.drawable.wallpaper4)
                .addImage(R.drawable.wallpaper5);
        int a = 0;
    }
}
