package com.ayokunlepaul.frescoloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.ayokunlepaul.scrollgalleryview.loader.MediaLoader;

/**
 * Created by ayokunlepaul on 05/01/2019.
 */
public class FrescoImageLoader implements MediaLoader {

    private String url;
    private Integer thumbnailWidth;
    private Integer thumbnailHeight;

    public FrescoImageLoader(String url) {
        this.url = url;
    }

    public FrescoImageLoader(String url, Integer thumbnailWidth, Integer thumbnailHeight) {
        this.url = url;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
    }

    @Override
    public boolean isImage() {
        return true;
    }

    @Override
    public void loadMedia(Context context, final ImageView imageView, SuccessCallback callback) {
        if (!Fresco.hasBeenInitialized()) {
            Fresco.initialize(context);
        }
        ImagePipeline pipeline = Fresco.getImagePipeline();
        DataSubscriber subscriber = getSubscriber(imageView, callback);
        DataSource<CloseableReference<CloseableImage>> dataSource = pipeline.fetchDecodedImage(createImageRequest(), context);
        dataSource.subscribe(subscriber, UiThreadImmediateExecutorService.getInstance());
    }

    @Override
    public void loadThumbnail(Context context, ImageView thumbnailView, SuccessCallback callback) {
        if (!Fresco.hasBeenInitialized()){
            Fresco.initialize(context);
        }
        ImagePipeline pipeline = Fresco.getImagePipeline();
        DataSubscriber subscriber = getSubscriber(thumbnailView, callback);
        DataSource<CloseableReference<CloseableImage>> dataSource = pipeline.fetchDecodedImage(createImageRequest(thumbnailWidth, thumbnailHeight), context);
        dataSource.subscribe(subscriber, UiThreadImmediateExecutorService.getInstance());
    }

    private ImageRequest createImageRequest() {
        return ImageRequest.fromUri(Uri.parse(url));
    }

    private ImageRequest createImageRequest(Integer thumbnailWidth, Integer thumbnailHeight) {
        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url));
        builder.setResizeOptions(new ResizeOptions(thumbnailWidth, thumbnailHeight));

        return builder.build();
    }

    private DataSubscriber getSubscriber(final ImageView imageView, final SuccessCallback callback) {
        return new BaseDataSubscriber<CloseableReference<CloseableBitmap>>() {
            @Override
            protected void onNewResultImpl(DataSource<CloseableReference<CloseableBitmap>> dataSource) {
                if (!dataSource.isFinished()) {
                    return;
                }
                CloseableReference<CloseableBitmap> result = dataSource.getResult();
                if (result != null) {
                    final CloseableReference<CloseableBitmap> resultCopy = result.clone();
                    try {
                        CloseableBitmap closeableBitmap = resultCopy.get();
                        Bitmap bitmap = closeableBitmap.getUnderlyingBitmap();
                        if (bitmap != null && !bitmap.isRecycled()) {
                            imageView.setImageBitmap(bitmap);
                            callback.onSuccess();
                        }
                    } finally {
                        result.close();
                        resultCopy.close();
                    }
                }
            }

            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableBitmap>> dataSource) {
                Throwable cause = dataSource.getFailureCause();
                if (cause != null) cause.printStackTrace();
            }
        };
    }
}
