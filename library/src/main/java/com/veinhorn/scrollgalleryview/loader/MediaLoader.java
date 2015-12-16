package com.veinhorn.scrollgalleryview.loader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Implementation may load image from some sources, such as remote url.
 */
public interface MediaLoader {

    /**
     * @return true if implementation load's image, otherwise false
     */
    boolean isImage();

    /**
     * Loads image and sets it to imageView. After that implementation can call callback to set imageView's
     * scale type to ScaleType.FIT_CENTER.
     */
    void loadMedia(Context context, ImageView imageView, SuccessCallback callback);

    /**
     * Loads thumbnail image and sets it to thumbnailView. After that implementation can call callback
     * to set thumbnailView's scale type to ScaleType.FIT_CENTER.
     */

    void loadThumbnail(Context context, ImageView thumbnailView, SuccessCallback callback);

    /**
     * Implementation may call this callback for report to imageView, what it's image was changed
     */
    interface SuccessCallback {
        void onSuccess();
    }
}