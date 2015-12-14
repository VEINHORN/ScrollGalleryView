package com.veinhorn.scrollgalleryview;

import android.content.Intent;
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

    private static final String IS_LOCKED = "isLocked";

    public static final String IS_VIDEO = "isVideo";

    public static final String URL = "url";

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
        backgroundImage = (ImageView)rootView.findViewById(R.id.backgroundImage);
        backgroundImage.setImageBitmap(mMediaInfo.getLoader().loadBitmap(getActivity()));
        if(getArguments().getBoolean(IS_VIDEO)) {
            backgroundImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayVideo(getArguments().getString(URL));
                }
            });
        }
        else if(getArguments().getBoolean("zoom")) {
            photoViewAttacher = new PhotoViewAttacher(backgroundImage);
        }
        viewPager = getViewPager();

        if(savedInstanceState != null) {
            boolean isLocked = savedInstanceState.getBoolean(IS_LOCKED, false);
            viewPager.setLocked(isLocked);
        }

        return rootView;
    }

    private void displayVideo(String url) {
        Intent intent = new Intent(this.getContext(),
                VideoPlayerActivity.class);
        intent.putExtra(URL, url);
        this.getContext().startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (isViewPagerActive()) {
            outState.putBoolean(IS_LOCKED, viewPager.isLocked());
        }
        super.onSaveInstanceState(outState);
    }

    private boolean isViewPagerActive() {
        return viewPager != null;
    }

    private HackyViewPager getViewPager() {
        return (HackyViewPager)getActivity().findViewById(R.id.viewPager);
    }
}
