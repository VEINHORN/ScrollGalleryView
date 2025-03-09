package com.veinhorn.scrollgalleryview.example.builder;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

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
                        "https://media.istockphoto.com/id/104241367/photo/cruise-ship.jpg?s=612x612&w=0&k=20&c=so7ce3cN0vhkKqIL38muc7rPqkp6WyNXWiWzuXH-mD4=",
                        "http://povodu.ru/wp-content/uploads/2016/04/pochemu-korabl-derzitsa-na-vode.jpg"
                ))
                .add(video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", R.mipmap.default_video))
                .build();
    }
}
