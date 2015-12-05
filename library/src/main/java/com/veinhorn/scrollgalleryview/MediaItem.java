package com.veinhorn.scrollgalleryview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.util.regex.Pattern;

/**
 * Author: Alexey Nevinsky
 * Date: 29.11.15 17:38
 */
public class MediaItem {
    private static final Pattern IMAGE_FILES_REGEX = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp)))");
    private static final Pattern VIDEO_FILES_REGEX = Pattern.compile("([^\\s]+(\\.(?i)(mp4|3gp|webm|mkv)))");
    private String mURL;
    private MediaLoader mLoader;

    public static MediaItem url(String url) {
        return new MediaItem().setURL(url);
    }

    public static MediaItem imageLoader(int id) {
        return new MediaItem().setLoader(new ImageLoader(id));
    }

    public static MediaItem imageLoader(Bitmap bitmap) {
        return new MediaItem().setLoader(new ImageLoader(bitmap));
    }

    public static MediaItem mediaLoader(MediaLoader mediaLoader) {
        return new MediaItem().setLoader(mediaLoader);
    }

    public boolean isImage() {
        return (mLoader instanceof ImageLoader || isImageURL(getURL()))
                && !isVideoURL(getURL());
    }

    private boolean isImageURL(String url) {
        if (url != null) {
            return IMAGE_FILES_REGEX.matcher(url).matches();
        }
        return false;
    }

    private boolean isVideoURL(String url) {
        if (url != null) {
            return VIDEO_FILES_REGEX.matcher(url).matches();
        }
        return false;
    }

    public boolean isVideo() {
        return isVideoURL(getURL());
    }

    public String getURL() {
        return mURL;
    }

    public MediaItem setURL(String URL) {
        mURL = URL;
        if (isVideoURL(mURL)) {
            setLoader(new ImageLoader(R.drawable.video_icon));
        }
        return this;
    }

    public MediaLoader getLoader() {
        return mLoader;
    }

    public MediaItem setLoader(MediaLoader loader) {
        mLoader = loader;
        return this;
    }


    public interface MediaLoader {
        Bitmap loadBitmap(Activity activity);
    }

    private static class ImageLoader implements MediaLoader {
        private int mId;
        private Bitmap mBitmap;

        public ImageLoader() {

        }

        public ImageLoader(int id) {
            mId = id;
        }

        public ImageLoader(Bitmap bitmap) {
            mBitmap = bitmap;
        }

        public Bitmap loadBitmap(Activity activity) {
            if (mBitmap == null) {
                mBitmap = ((BitmapDrawable) activity.getResources().getDrawable(mId)).getBitmap();
            }
            return mBitmap;
        }
    }
}
