package com.veinhorn.scrollgalleryview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by veinhorn on 6.8.15.
 */
public class ScrollGalleryView extends LinearLayout {
    private Context context;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout thumbnailsContainer;
    private WindowManager windowManager;

    private int thumbnailSize; // width and height in pixels

    public ScrollGalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        setOrientation(VERTICAL);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.scroll_gallery_view, this, true);

        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.thumbnails_scroll_view);

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        thumbnailsContainer = (LinearLayout)findViewById(R.id.thumbnails_container);
        thumbnailsContainer.setPadding(display.getWidth() / 2, 0,
                display.getWidth() / 2, 0);
    }

    public ScrollGalleryView addThumbnail(int image) {
        ImageView thumbnail = new ImageView(context);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(thumbnailSize, thumbnailSize);
        thumbnail.setLayoutParams(layoutParams);
        thumbnail.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        thumbnail.setPadding(10, 10, 10, 10); // add method for setting
        thumbnail.setImageDrawable(getResources().getDrawable(image));

        thumbnail.setOnClickListener(thumbnailOnClickListener);
        thumbnailsContainer.addView(thumbnail);
        return this;
    }

    public ScrollGalleryView addImage(int image) {
        addThumbnail(image);
        return this;
    }

    public ScrollGalleryView setThumbnailSize(int thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
        return this;
    }

    private OnClickListener thumbnailOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Display display = windowManager.getDefaultDisplay();
            int thumbnailCoords[] = new int [2];
            v.getLocationOnScreen(thumbnailCoords);

            int thumbnailCenterX = thumbnailCoords[0] + thumbnailSize / 2;
            int thumbnailDelta = display.getWidth() / 2 - thumbnailCenterX;

            int coords[] = new int [2];
            if(thumbnailCenterX > display.getWidth() / 2) horizontalScrollView.smoothScrollBy(-thumbnailDelta, 0);
            else horizontalScrollView.smoothScrollBy(-thumbnailDelta, 0);
        }
    };
}
