# ScrollGalleryView

Create awesome image gallery using ScrollGalleryView. Just add your images.

![ScrollGalleryView](http://i.imgur.com/xrBt4Xx.gif)

##Features
- Easy way to select images in gallery (thumbnails)
- Zooming
- Thumbnail borders

## Sample application
The sample application published on Google Play.

[![Get it on Google Play](http://www.android.com/images/brand/get_it_on_play_logo_small.png)](http://play.google.com)

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
                .setFragmentManager(getSupportFragmentManager())
                .setThumbnailSize(100)
                .addImage(R.drawable.wallpaper1)
                .addImage(R.drawable.wallpaper2)
                .addImage(R.drawable.wallpaper3)
                .addImage(R.drawable.wallpaper4)
                .addImage(R.drawable.wallpaper5)
                .addImage(R.drawable.wallpaper6)
                .addImage(R.drawable.wallpaper7);
    }
}
```

## License

    Copyright 2011, 2012 Chris Banes

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
