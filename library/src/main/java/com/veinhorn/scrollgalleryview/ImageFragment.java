package com.veinhorn.scrollgalleryview;

import android.graphics.Bitmap;
import android.os.Bundle;
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
    private ImageView backgroundImage;
    private PhotoViewAttacher photoViewAttacher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.image_fragment, container, false);
        backgroundImage = (ImageView)rootView.findViewById(R.id.backgroundImage);
        backgroundImage.setImageBitmap((Bitmap)getArguments().getParcelable("image"));
        if(getArguments().getBoolean("isZoom")) photoViewAttacher = new PhotoViewAttacher(backgroundImage);
        return rootView;
    }
}
