# ScrollGalleryView

![](https://travis-ci.org/inver/ScrollGalleryView.svg)
[![Join the chat at https://gitter.im/VEINHORN/ScrollGalleryView](https://badges.gitter.im/VEINHORN/ScrollGalleryView.svg)](https://gitter.im/VEINHORN/ScrollGalleryView?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Android library for creating image/video gallery with thumbnails on bottom of the screen. Just add your images using simple API.

![ScrollGalleryView](http://i.imgur.com/xrBt4Xx.gif)

##Features
- Easy way to select images in gallery (thumbnails)
- Zooming
- Simple API
- Video

## Sample application
The sample application published on Google Play.

[![Get it on Google Play](http://www.android.com/images/brand/get_it_on_play_logo_small.png)](https://play.google.com/store/apps/details?id=com.veinhorn.scrollgalleryview)

## Gradle Dependency
```gradle
compile 'com.veinhorn.scrollgalleryview:library:1.0.5'
```

##Usage
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <com.veinhorn.scrollgalleryview.ScrollGalleryView
        android:id="@+id/scroll_gallery_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#000"/>
</LinearLayout>
```

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

## License

    Copyright 2015, 2015 Boris Korogvich

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
