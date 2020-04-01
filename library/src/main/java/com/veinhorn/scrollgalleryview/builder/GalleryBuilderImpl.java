package com.veinhorn.scrollgalleryview.builder;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;

import java.util.ArrayList;
import java.util.List;

public class GalleryBuilderImpl implements GalleryBuilder {
    private ScrollGalleryView galleryView;
    private GallerySettings settings;

    private ScrollGalleryView.OnImageClickListener onImageClickListener;
    private ScrollGalleryView.OnImageLongClickListener onImageLongClickListener;

    private List<MediaInfo> medias;

    public GalleryBuilderImpl(ScrollGalleryView galleryView) {
        this.galleryView = galleryView;
        this.medias = new ArrayList<>();
    }

    @Override
    public GalleryBuilder settings(GallerySettings settings) {
        this.settings = settings;
        return this;
    }

    @Override
    public GalleryBuilder onImageClickListener(ScrollGalleryView.OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
        return this;
    }

    @Override
    public GalleryBuilder onImageLongClickListener(ScrollGalleryView.OnImageLongClickListener onImageLongClickListener) {
        this.onImageLongClickListener = onImageLongClickListener;
        return this;
    }

    @Override
    public GalleryBuilder add(MediaInfo media) {
        this.medias.add(media);
        return this;
    }

    @Override
    public GalleryBuilder add(List<MediaInfo> medias) {
        this.medias.addAll(medias);
        return this;
    }

    @Override
    public ScrollGalleryView build() {
        // check here all parameters

        return galleryView
                .setThumbnailSize(settings.getThumbnailSize())
                .setZoom(settings.isZoomEnabled())
                .addOnImageClickListener(onImageClickListener)
                .addOnImageLongClickListener(onImageLongClickListener)
                .setFragmentManager(settings.getFragmentManager())
                .addMedia(medias);
    }
}
