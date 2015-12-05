package com.veinhorn.scrollgalleryview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.util.regex.Pattern;

/**
 * Media Info contains the information required to load and isplay the media in the gallery.
 */
public class MediaInfo {

    private static final Pattern IMAGE_FILES_REGEX = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp)))");
    private static final Pattern VIDEO_FILES_REGEX = Pattern.compile("([^\\s]+(\\.(?i)(mp4|3gp|webm|mkv)))");
    private String mURL;
    private MediaLoader mLoader;

    public static MediaInfo url(String url) {
        return new MediaInfo().setURL(url);
    }

    public static MediaInfo imageLoader(int id) {
        return new MediaInfo().setLoader(new ImageLoader(id));
    }

    public static MediaInfo imageLoader(Bitmap bitmap) {
        return new MediaInfo().setLoader(new ImageLoader(bitmap));
    }

    public static MediaInfo mediaLoader(MediaLoader mediaLoader) {
        return new MediaInfo().setLoader(mediaLoader);
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

    public MediaInfo setURL(String URL) {
        mURL = URL;
        if (isVideoURL(mURL)) {
            setLoader(new ImageLoader(R.drawable.video_icon));
        }
        return this;
    }

    public MediaLoader getLoader() {
        return mLoader;
    }

    public MediaInfo setLoader(MediaLoader loader) {
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
                mBitmap = ((BitmapDrawable) activity
                        .getResources()
                        .getDrawable(mId)).getBitmap();
            }
            return mBitmap;
        }
    }
}
