package com.veinhorn.scrollgalleryview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

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
        return new MediaInfo().setLoader(new DefaultImageLoader(id));
    }

    public static MediaInfo imageLoader(Bitmap bitmap) {
        return new MediaInfo().setLoader(new DefaultImageLoader(bitmap));
    }

    public static MediaInfo mediaLoader(MediaLoader mediaLoader) {
        return new MediaInfo().setLoader(mediaLoader);
    }

    public boolean isImage() {
        return (mLoader instanceof DefaultImageLoader || isImageURL(getURL()))
                && !isVideoURL(getURL());
    }

    private boolean isImageURL(String url) {
        return url != null && IMAGE_FILES_REGEX.matcher(url).matches();
    }

    private boolean isVideoURL(String url) {
        return url != null && VIDEO_FILES_REGEX.matcher(url).matches();
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
            setLoader(new DefaultImageLoader(R.drawable.video_icon));
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
        void loadMedia(Context context, ImageView imageView);

        void loadThumbnail(Context context, ImageView thumbnailView);
    }

    public static class DefaultImageLoader implements MediaLoader {
        private int mId;
        private Bitmap mBitmap;

        public DefaultImageLoader(int id) {
            mId = id;
        }

        public DefaultImageLoader(Bitmap bitmap) {
            mBitmap = bitmap;
        }

        @Override
        public void loadMedia(Context context, ImageView imageView) {
            //we aren't loading bitmap, because full image loaded on thumbnail step
            imageView.setImageBitmap(mBitmap);
        }

        @Override
        public void loadThumbnail(Context context, ImageView thumbnailView) {
            loadBitmap(context);
            thumbnailView.setImageBitmap(mBitmap);
        }

        private void loadBitmap(Context context) {
            if (mBitmap == null) {
                mBitmap = ((BitmapDrawable) context.getResources().getDrawable(mId)).getBitmap();
            }
        }
    }

}
