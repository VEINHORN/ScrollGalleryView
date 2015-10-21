package com.veinhorn.scrollgalleryview;

import android.graphics.Bitmap;
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
    private HackyViewPager viewPager;
    private ImageView backgroundImage;
    private PhotoViewAttacher photoViewAttacher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.image_fragment, container, false);
        backgroundImage = (ImageView)rootView.findViewById(R.id.backgroundImage);
        backgroundImage.setImageBitmap((Bitmap) getArguments().getParcelable("image"));
        if(getArguments().getBoolean("zoom")) photoViewAttacher = new PhotoViewAttacher(backgroundImage);
        viewPager = getViewPager();

        if(savedInstanceState != null) {
            boolean isLocked = savedInstanceState.getBoolean(IS_LOCKED, false);
            viewPager.setLocked(isLocked);
        }

        return rootView;
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
