package com.veinhorn.scrollgalleryview;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 6.8.15.
 */
public class ScrollGalleryView extends LinearLayout {
    private FragmentManager fragmentManager;
    private Context context;
    private WindowManager windowManager;
    private int thumbnailSize; // width and height in pixels

    // Views
    private LinearLayout thumbnailsContainer;
    private HorizontalScrollView horizontalScrollView;
    private ViewPager viewPager;
    ////////
    private PagerAdapter pagerAdapter;
    private List<Integer> images;


    public ScrollGalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        images = new ArrayList<>();

        setOrientation(VERTICAL);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.scroll_gallery_view, this, true);

        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.thumbnails_scroll_view);

        thumbnailsContainer = (LinearLayout)findViewById(R.id.thumbnails_container);
        thumbnailsContainer.setPadding(display.getWidth() / 2, 0,
                display.getWidth() / 2, 0);
    }

    public ScrollGalleryView setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        initializeViewPager();
        return this;
    }

    public ScrollGalleryView addImage(int image) {
        images.add(image);
        addThumbnail(image);
        pagerAdapter.notifyDataSetChanged();
        return this;
    }

    public ScrollGalleryView setThumbnailSize(int thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
        return this;
    }

    private OnClickListener thumbnailOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            scroll(v);
            viewPager.setCurrentItem((int)v.getTag(), true);
        }
    };

    private ViewPager.SimpleOnPageChangeListener viewPagerChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            scroll(thumbnailsContainer.getChildAt(position));
        }
    };

    private ScrollGalleryView addThumbnail(int image) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(thumbnailSize, thumbnailSize);
        layoutParams.setMargins(10, 10, 10, 10);

        ImageView thumbnail = new ImageView(context);
        thumbnail.setLayoutParams(layoutParams);
        thumbnail.setScaleType(ImageView.ScaleType.CENTER_CROP);
        thumbnail.setImageDrawable(getResources().getDrawable(image));
        thumbnail.setTag(images.size() - 1);
        thumbnail.setOnClickListener(thumbnailOnClickListener);

        thumbnailsContainer.addView(thumbnail);
        return this;
    }

    private void initializeViewPager() {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        pagerAdapter = new ScreenSlidePagerAdapter(fragmentManager, images);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerChangeListener);
    }

    private void scroll(View thumbnail) {
        Display display = windowManager.getDefaultDisplay();
        int thumbnailCoords[] = new int [2];
        thumbnail.getLocationOnScreen(thumbnailCoords);

        int thumbnailCenterX = thumbnailCoords[0] + thumbnailSize / 2;
        int thumbnailDelta = display.getWidth() / 2 - thumbnailCenterX;

        horizontalScrollView.smoothScrollBy(-thumbnailDelta, 0);
    }
}
