package com.veinhorn.scrollgalleryview.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

/**
 * Default implementation of MediaLoader for images from id or bitmap
 */
public class DefaultImageLoader implements MediaLoader {
    private int mId;
    private Bitmap mBitmap;

    public DefaultImageLoader(int id) {
        mId = id;
    }

    public DefaultImageLoader(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    @Override
    public boolean isImage() {
        return true;
    }

    @Override
    public void loadMedia(Context context, ImageView imageView, SuccessCallback callback) {
        //we aren't loading bitmap, because full image loaded on thumbnail step
        imageView.setImageBitmap(mBitmap);
        if (callback != null) {
            callback.onSuccess();
        }
    }

    @Override
    public void loadThumbnail(Context context, ImageView thumbnailView, SuccessCallback callback) {
        loadBitmap(context);
        thumbnailView.setImageBitmap(mBitmap);
        if (callback != null) {
            callback.onSuccess();
        }
    }

    private void loadBitmap(Context context) {
        if (mBitmap == null) {
            mBitmap = ((BitmapDrawable) context.getResources().getDrawable(mId)).getBitmap();
        }
    }
}