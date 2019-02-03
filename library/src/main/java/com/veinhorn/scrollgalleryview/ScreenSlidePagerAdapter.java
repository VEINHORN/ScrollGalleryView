package com.veinhorn.scrollgalleryview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

/**
 * Created by veinhorn on 29.8.15.
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<MediaInfo> mListOfMedia;

    private boolean isZoom = false;

    private ScrollGalleryView.OnImageClickListener onImageClickListener;
    private ScrollGalleryView.OnImageLongClickListener onImageLongClickListener;

    public ScreenSlidePagerAdapter(FragmentManager fm,
                                   List<MediaInfo> listOfMedia,
                                   boolean isZoom,
                                   ScrollGalleryView.OnImageClickListener onImageClickListener,
                                   ScrollGalleryView.OnImageLongClickListener onImageLongClickListener) {
        super(fm);
        this.mListOfMedia = listOfMedia;
        this.isZoom = isZoom;
        this.onImageClickListener = onImageClickListener;
        this.onImageLongClickListener = onImageLongClickListener;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position < mListOfMedia.size()) {
            MediaInfo mediaInfo = mListOfMedia.get(position);
            fragment = loadImageFragment(mediaInfo);
        }
        return fragment;
    }

    private Fragment loadImageFragment(MediaInfo mediaInfo) {
        ImageFragment fragment = new ImageFragment();
        fragment.setRetainInstance(true);
        fragment.setMediaInfo(mediaInfo);

        if (onImageClickListener != null) {
            fragment.setOnImageClickListener(onImageClickListener);
        }
        if (onImageLongClickListener != null) {
            fragment.setOnImageLongClickListener(onImageLongClickListener);
        }

        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.ZOOM, isZoom);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void removeItem(int position) {
        mListOfMedia.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListOfMedia.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
