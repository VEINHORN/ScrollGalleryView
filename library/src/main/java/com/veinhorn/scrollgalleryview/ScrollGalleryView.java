package com.veinhorn.scrollgalleryview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.veinhorn.scrollgalleryview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 6.8.15.
 */
public class ScrollGalleryView extends LinearLayout {
    private Context context;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout thumbnailsContainer;

    private Integer thumbnailsCount;
    private Integer curThumbnailPos;

    private int thumbnailSize; // width and height in pixels

    public ScrollGalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        setOrientation(VERTICAL);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.scroll_gallery_view, this, true);

        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.thumbnails_scroll_view);

        // ===
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        thumbnailsContainer = (LinearLayout)findViewById(R.id.thumbnails_container);
        thumbnailsContainer.setPadding(display.getWidth() / 2 - 100, 0,
                display.getWidth() / 2 - 100, 0);
        // ===

        curThumbnailPos = 0;
        thumbnailsCount = 0;
    }

    public ScrollGalleryView addThumbnail(int image) {
        ImageView thumbnail = new ImageView(context);
        thumbnail.setTag(thumbnailsCount++);
        thumbnail.setPadding(10, 10, 10, 10); // add method for setting
        thumbnail.setImageDrawable(getResources().getDrawable(image));
        thumbnail.setOnClickListener(thumbnailOnClickListener);
        thumbnailsContainer.addView(thumbnail);
        return this;
    }

    public ScrollGalleryView setThumbnailSize(int thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
        return this;
    }

    private OnClickListener thumbnailOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int clickedThumbnailPos = (int)v.getTag();
            if(clickedThumbnailPos > curThumbnailPos) horizontalScrollView.smoothScrollBy(2 * thumbnailSize, 0);
            else if(clickedThumbnailPos < curThumbnailPos) horizontalScrollView.smoothScrollBy(-2 * thumbnailSize, 0);
            curThumbnailPos = clickedThumbnailPos;
        }
    };
}
