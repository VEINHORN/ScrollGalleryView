package com.veinhorn.example;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;

public class MainActivity extends FragmentActivity {
    private ScrollGalleryView scrollGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollGalleryView = (ScrollGalleryView)findViewById(R.id.scroll_gallery_view);
        scrollGalleryView
                .setFragmentManager(getSupportFragmentManager())
                .setThumbnailSize(100)
                .addImage(R.drawable.wallpaper1)
                .addImage(R.drawable.wallpaper2)
                .addImage(R.drawable.wallpaper3)
                .addImage(R.drawable.wallpaper4)
                .addImage(R.drawable.wallpaper5)
                .addImage(R.drawable.wallpaper6)
                .addImage(R.drawable.wallpaper7);
    }
}
