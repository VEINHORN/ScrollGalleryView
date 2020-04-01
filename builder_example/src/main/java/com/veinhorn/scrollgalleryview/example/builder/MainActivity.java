package com.veinhorn.scrollgalleryview.example.builder;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.veinhorn.scrollgalleryview.ScrollGalleryView;
import com.veinhorn.scrollgalleryview.builder.GallerySettings;

import static com.veinhorn.scrollgalleryview.loader.picasso.dsl.DSL.*;

public class MainActivity extends FragmentActivity {
    private ScrollGalleryView galleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        galleryView = ScrollGalleryView
                .from((ScrollGalleryView) findViewById(R.id.scroll_gallery_view))
                .settings(
                        GallerySettings
                                .from(getSupportFragmentManager())
                                .thumbnailSize(100)
                                .enableZoom(true)
                                .build()
                )
                .onImageClickListener(new ScrollGalleryView.OnImageClickListener() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(MainActivity.this, "image position = " + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .onImageLongClickListener(new ScrollGalleryView.OnImageLongClickListener() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(MainActivity.this, "image position = " + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .add(images(
                        "http://pirate-islands.com/wp-content/uploads/2018/07/07_Dom-Fernando-II_01-636x310.jpg",
                        "http://povodu.ru/wp-content/uploads/2016/04/pochemu-korabl-derzitsa-na-vode.jpg"
                ))
                .add(video("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4", R.mipmap.default_video))
                .build();
    }
}
