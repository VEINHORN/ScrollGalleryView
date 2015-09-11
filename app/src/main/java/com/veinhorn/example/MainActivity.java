package com.veinhorn.example;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
                .setThumbnailSize(100)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager())
                .addImage(R.drawable.wallpaper1)
                .addImage(R.drawable.wallpaper2)
                .addImage(R.drawable.wallpaper3)
                .addImage(R.drawable.wallpaper4)
                .addImage(R.drawable.wallpaper5)
                .addImage(R.drawable.wallpaper6)
                .addImage(convertDrawableToBitmap(R.drawable.wallpaper7));
    }

    private Bitmap convertDrawableToBitmap(int image) {
        return ((BitmapDrawable)getResources().getDrawable(image)).getBitmap();
    }
}
