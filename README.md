# ScrollGalleryView

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
    private ScrollGalleryView scrollGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollGalleryView = (ScrollGalleryView)findViewById(R.id.scroll_gallery_view);
        scrollGalleryView
                .setThumbnailSize(100)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager())
                .addImage(R.drawable.wallpaper1)
                .addImage(R.drawable.wallpaper2)
                .addImage(R.drawable.wallpaper3)
                .addImage(R.drawable.wallpaper4)
                .addImage(R.drawable.wallpaper5)
                .addImage(R.drawable.wallpaper6)
                .addImage(convertDrawableToBitmap(R.drawable.wallpaper7));
    }

    private Bitmap convertDrawableToBitmap(int image) {
        return ((BitmapDrawable)getResources().getDrawable(image)).getBitmap();
    }
}

```

## Gradle integration
```gradle
compile 'com.veinhorn.scrollgalleryview:library:1.0.3'
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
