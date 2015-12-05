package com.veinhorn.scrollgalleryview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by veinhorn on 29.8.15.
 */
public class ImageFragment extends Fragment {

    private MediaInfo mMediaInfo;

    private HackyViewPager viewPager;
    private ImageView backgroundImage;
    private PhotoViewAttacher photoViewAttacher;

    public void setMediaInfo(MediaInfo mediaInfo) {
        mMediaInfo = mediaInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.image_fragment, container, false);
        backgroundImage = (ImageView) rootView.findViewById(R.id.backgroundImage);
        viewPager = (HackyViewPager) getActivity().findViewById(R.id.viewPager);

        if (savedInstanceState != null) {
            boolean isLocked = savedInstanceState.getBoolean(Constants.IS_LOCKED, false);
            viewPager.setLocked(isLocked);
            backgroundImage.setImageBitmap((Bitmap) savedInstanceState.getParcelable(Constants.IMAGE));
        }

        loadImageToView();

        return rootView;
    }

    private void loadImageToView() {
        if (mMediaInfo != null) {
            mMediaInfo.getLoader().loadMedia(getActivity(), backgroundImage);
        }
        if (getArguments().getBoolean(Constants.IS_VIDEO)) {
            backgroundImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayVideo(getArguments().getString(Constants.URL));
                }
            });
        } else if (getArguments().getBoolean(Constants.ZOOM)) {
            photoViewAttacher = new PhotoViewAttacher(backgroundImage);
        }
    }

    private void displayVideo(String url) {
        Intent intent = new Intent(this.getContext(), VideoPlayerActivity.class);
        intent.putExtra(Constants.URL, url);
        this.getContext().startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (isViewPagerActive()) {
            outState.putBoolean(Constants.IS_LOCKED, viewPager.isLocked());
        }
        outState.putParcelable(Constants.IMAGE, ((BitmapDrawable) backgroundImage.getDrawable()).getBitmap());
        super.onSaveInstanceState(outState);
    }

    private boolean isViewPagerActive() {
        return viewPager != null;
    }
}
