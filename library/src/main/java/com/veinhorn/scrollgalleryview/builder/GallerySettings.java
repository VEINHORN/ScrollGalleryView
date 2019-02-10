package com.veinhorn.scrollgalleryview.builder;

import android.support.v4.app.FragmentManager;

public class GallerySettings {
    private int thumbnailSize;
    private boolean isZoomEnabled;
    private boolean isAutoSelectionEnabled;
    private FragmentManager fragmentManager;

    public int getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(int thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    public boolean isZoomEnabled() {
        return isZoomEnabled;
    }

    public void setZoomEnabled(boolean zoomEnabled) {
        isZoomEnabled = zoomEnabled;
    }

    public void setAutoSelectionEnabled(boolean autoSelectionEnabled) {
        isAutoSelectionEnabled = autoSelectionEnabled;
    }

    public boolean isAutoSelectionEnabled() {
        return isAutoSelectionEnabled;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static GallerySettingsBuilder from(FragmentManager fm) {
        GallerySettingsBuilder builder = new GallerySettingsBuilderImpl();
        builder.withFragmentManager(fm);
        return builder;
    }
}
