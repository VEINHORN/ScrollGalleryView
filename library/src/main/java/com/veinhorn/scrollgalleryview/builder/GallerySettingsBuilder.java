package com.veinhorn.scrollgalleryview.builder;

import androidx.fragment.app.FragmentManager;

public interface GallerySettingsBuilder {
    GallerySettingsBuilder thumbnailSize(int thumbnailSize);
    GallerySettingsBuilder enableZoom(boolean isZoomEnabled);
    GallerySettingsBuilder withFragmentManager(FragmentManager fragmentManager);
    GallerySettings build();
}
