# Initializing image gallery

There are 2 ways of initializing image gallery powered by *ScrollGalleryView* library:

- using fluent API (**experimental**)
- using old initialization method

You can use one of them depending on your needs.

## Fluent API (experimental)

From version `1.2.0` and higher *ScrollGalleryView* library includes fluent API which simplifies image gallery initialization.

### Add dependencies

Add dependencies to your project (you can use *Picasso*, *Glide* or *Fresco* loaders):

```gradle
implementation 'com.veinhorn.scrollgalleryview:library:1.2.0'
implementation 'com.veinhorn.scrollgalleryview:picasso-loader:1.2.0'
```

### Usage

Here we use `media()` and `video()` methods from `picasso-loader`:

```java
import static com.veinhorn.scrollgalleryview.loader.picasso.dsl.DSL.*;

galleryView = ScrollGalleryView
                .from((ScrollGalleryView) findViewById(R.id.scroll_gallery_view))
                .settings(
                        GallerySettings
                                .from(getSupportFragmentManager())
                                .thumbnailSize(100)
                                .enableZoom(true)
                                .build()
                )
                .add(media("http://pirate-islands.com/wp-content/uploads/2018/07/07_Dom-Fernando-II_01-636x310.jpg"))
                .add(media("http://povodu.ru/wp-content/uploads/2016/04/pochemu-korabl-derzitsa-na-vode.jpg"))
                .add(video("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4"))
                .build();
```

There is also `GalleryBuilder add(MediaInfo mediaInfo);` method from `GalleryBuilder` which you can use to populate gallery with media.

## Old initialization method

You can also use old initialization method which is supported by all versions of ScrollGalleryView library.

### Usage

```java
public class MainActivity extends FragmentActivity {

    private static final ArrayList<String> images = new ArrayList<>(Arrays.asList(
            "http://img1.goodfon.ru/original/1920x1080/d/f5/aircraft-jet-su-47-berkut.jpg",
            "http://www.dishmodels.ru/picture/glr/13/13312/g13312_7657277.jpg",
            "http://img2.goodfon.ru/original/1920x1080/b/c9/su-47-berkut-c-37-firkin.jpg"
    ));
    private static final String movieUrl = "http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4";

    private ScrollGalleryView scrollGalleryView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<MediaInfo> infos = new ArrayList<>(images.size());
        for (String url : images) infos.add(MediaInfo.mediaLoader(new PicassoImageLoader(url)));

        scrollGalleryView = (ScrollGalleryView) findViewById(R.id.scroll_gallery_view);
        scrollGalleryView
                .setThumbnailSize(100)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager())
                .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.drawable.wallpaper1)))
                .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(toBitmap(R.drawable.wallpaper7))))
                .addMedia(MediaInfo.mediaLoader(new MediaLoader() {
                    @Override public boolean isImage() {
                        return true;
                    }

                    @Override public void loadMedia(Context context, ImageView imageView,
                                                    MediaLoader.SuccessCallback callback) {
                        imageView.setImageBitmap(toBitmap(R.drawable.wallpaper3));
                        callback.onSuccess();
                    }

                    @Override public void loadThumbnail(Context context, ImageView thumbnailView,
                                                        MediaLoader.SuccessCallback callback) {
                        thumbnailView.setImageBitmap(toBitmap(R.drawable.wallpaper3));
                        callback.onSuccess();
                    }
                }))
                .addMedia(MediaInfo.mediaLoader(new DefaultVideoLoader(movieUrl, R.mipmap.default_video)))
                .addMedia(infos);
    }

    private Bitmap toBitmap(int image) {
        return ((BitmapDrawable) getResources().getDrawable(image)).getBitmap();
    }
}
```

