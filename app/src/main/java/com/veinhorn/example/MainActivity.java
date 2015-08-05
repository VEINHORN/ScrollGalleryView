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
                .setThumbnailSize(100)
                .addThumbnail(R.drawable.thumbnail1)
                .addThumbnail(R.drawable.thumbnail1)
                .addThumbnail(R.drawable.thumbnail1)
                .addThumbnail(R.drawable.thumbnail1)
                .addThumbnail(R.drawable.thumbnail1)
                .addThumbnail(R.drawable.thumbnail1)
                .addThumbnail(R.drawable.thumbnail1);
        int a = 0;
    }
}
