# ScrollGalleryView

![](https://travis-ci.org/inver/ScrollGalleryView.svg)
[![Join the chat at https://gitter.im/VEINHORN/ScrollGalleryView](https://badges.gitter.im/VEINHORN/ScrollGalleryView.svg)](https://gitter.im/VEINHORN/ScrollGalleryView?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Android library for creating image gallery with thumbnails on bottom of the screen. Just add your images using simple API.

![ScrollGalleryView](http://i.imgur.com/xrBt4Xx.gif)

##Features
- Easy way to select images in gallery (thumbnails)
- Zooming
- Simple API
- Thumbnail borders

## Sample application
The sample application published on Google Play.

[![Get it on Google Play](http://www.android.com/images/brand/get_it_on_play_logo_small.png)](https://play.google.com/store/apps/details?id=com.veinhorn.scrollgalleryview)

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
            "http://img2.goodfon.ru/original/1920x1080/b/c9/su-47-berkut-c-37-firkin.jpg",
            "http://www.avsimrus.com/file_images/15/img4951_1.jpg",
            "http://www.avsimrus.com/file_images/15/img4951_3.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/0/07/Sukhoi_Su-47_in_2008.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/b/b4/Sukhoi_Su-47_Berkut_%28S-37%29_in_2001.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37333-4.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37-0.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37-6.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37-9.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37-2.jpg",
            "http://testpilot.ru/russia/sukhoi/s/37/images/s37-1.jpg"
    ));
    
    private ScrollGalleryView scrollGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = convertDrawableToBitmap(R.drawable.wallpaper7);

        List<MediaInfo> infos = new ArrayList<>(images.size());
        for (String url : images) {
            infos.add(MediaInfo.mediaLoader(new PicassoImageLoader(url)));
        }

        scrollGalleryView = (ScrollGalleryView) findViewById(R.id.scroll_gallery_view);
        scrollGalleryView
                .setThumbnailSize(100)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager())
                .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.drawable.wallpaper1)))
                .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(bitmap)))
                .addMedia(MediaInfo.mediaLoader(new MediaLoader() {
                    @Override
                    public boolean isImage() {
                        return true;
                    }

                    @Override
                    public void loadMedia(Context context, ImageView imageView, MediaLoader.SuccessCallback callback) {
                        Bitmap bitmap = convertDrawableToBitmap(R.drawable.wallpaper3);
                        imageView.setImageBitmap(bitmap);
                        callback.onSuccess();
                    }

                    @Override
                    public void loadThumbnail(Context context, ImageView thumbnailView, MediaLoader.SuccessCallback                                 callback) {
                        Bitmap bitmap = convertDrawableToBitmap(R.drawable.wallpaper3);
                        thumbnailView.setImageBitmap(bitmap);
                        callback.onSuccess();
                    }
                }))
                .addMedia(MediaInfo.mediaLoader(
                        new DefaultVideoLoader("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4",                            R.mipmap.default_video)))
                .addMedia(infos);
    }

    private Bitmap convertDrawableToBitmap(int image) {
        return ((BitmapDrawable)getResources().getDrawable(image)).getBitmap();
    }
}

```

## Gradle integration
```gradle
compile 'com.veinhorn.scrollgalleryview:library:1.0.5'
```

## Dependencies
[Android Support Library](http://developer.android.com/tools/support-library/index.html)

[PhotoView](https://github.com/chrisbanes/PhotoView)

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
