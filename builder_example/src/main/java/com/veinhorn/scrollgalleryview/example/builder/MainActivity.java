package com.veinhorn.scrollgalleryview.example.builder;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;
import com.veinhorn.scrollgalleryview.builder.GallerySettings;
import com.veinhorn.scrollgalleryview.loader.picasso.PicassoImageLoader;

import static com.veinhorn.scrollgalleryview.loader.picasso.dsl.DSL.*;

public class MainActivity extends FragmentActivity {
    private static final String TAG = MainActivity.class.getName();

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
                                .enableAutoSelection(true)
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
                .onPageChangeListener(new CustomOnPageListener())
                .add(image("http://povodu.ru/wp-content/uploads/2016/04/pochemu-korabl-derzitsa-na-vode.jpg"))
                .add(video("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4", R.mipmap.default_video))
                .add(image(
                        "https://i.pinimg.com/originals/1b/d3/f0/1bd3f0e146da86f9c504e89a0b7e1403.jpg",
                        "Old Ship"
                ))
                .build();

                galleryView.addMedia(MediaInfo.mediaLoader(
                        new PicassoImageLoader("https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/USRC_Salmon_P_Chase_-_LoC_4a25817u.jpg/1200px-USRC_Salmon_P_Chase_-_LoC_4a25817u.jpg"),
                        "The word barque entered English via French, which in turn came from the Latin barca by way of Occitan, Catalan, Spanish or Italian."
                ));
    }

    private class CustomOnPageListener extends ViewPager.SimpleOnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            Log.i(TAG, "page selected #" + position);
        }
    }
}
