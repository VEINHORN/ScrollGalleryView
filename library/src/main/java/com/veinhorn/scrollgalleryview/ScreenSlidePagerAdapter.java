package com.veinhorn.scrollgalleryview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by veinhorn on 29.8.15.
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private List<Integer> images;

    public ScreenSlidePagerAdapter(FragmentManager fm, List<Integer> images) {
        super(fm);
        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("image", images.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
