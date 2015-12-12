package com.veinhorn.example;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.veinhorn.scrollgalleryview.MediaInfo;

/**
 * Author: Alexey Nevinsky
 * Date: 06.12.15 1:38
 */
public class PicassoImageLoader implements MediaInfo.MediaLoader {

    @Override
    public void loadMedia(Context context, ImageView imageView) {
        String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/f/f1/Sukhoi_Su-35S_in_2009_%282%29.jpg";
        Picasso.with(context)
                .load(imageUrl)
                .into(imageView);
    }

    @Override
    public void loadThumbnail(Context context, ImageView thumbnailView) {
        loadMedia(context, thumbnailView);
    }
}
