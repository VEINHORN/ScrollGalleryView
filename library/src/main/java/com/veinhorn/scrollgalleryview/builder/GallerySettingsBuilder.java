package com.veinhorn.scrollgalleryview.builder;

import android.support.v4.app.FragmentManager;

public interface GallerySettingsBuilder {
    GallerySettingsBuilder thumbnailSize(int thumbnailSize);
    GallerySettingsBuilder enableZoom(boolean isZoomEnabled);
    GallerySettingsBuilder enableAutoSelection(boolean isAutoSelectionEnabled);
    GallerySettingsBuilder withFragmentManager(FragmentManager fragmentManager);
    GallerySettings build();
}
