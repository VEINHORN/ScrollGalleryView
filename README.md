# ScrollGalleryView

[![Build Status](https://travis-ci.org/VEINHORN/ScrollGalleryView.svg?branch=master)](https://travis-ci.org/VEINHORN/ScrollGalleryView)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ScrollGalleryView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/2472)
[![Join the chat at https://gitter.im/VEINHORN/ScrollGalleryView](https://badges.gitter.im/VEINHORN/ScrollGalleryView.svg)](https://gitter.im/VEINHORN/ScrollGalleryView?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

ScrollGalleryView is a flexible library which helps you to create awesome media galleries in your Android application. It's easily integrated with the most popular image loading libraries such as Picasso, Glide and Fresco.

![ScrollGalleryView](http://i.imgur.com/xrBt4Xx.gif)

## Key features

- Easy way to select images in gallery (thumbnails)
- Zooming
- Simple API
- Video

## Installing

Add to your application `build.gradle`:

```gradle
compile 'com.veinhorn.scrollgalleryview:library:1.0.7'
```

## Usage

Add to your layout:

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

Then in your activity:

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
### Adding media

ScrollGalleryView supports different types of media such as images and videos. You can create image gallery, video gallery, or mix them in any way. To abstract from concreate way of image loading ScrollGalleryView uses [MediaLoader](library/src/main/java/com/veinhorn/scrollgalleryview/loader/MediaLoader.java) so it makes possible to use different image loading libraries depending on your needs (Picasso, Glide, Fresco).

#### **Picasso** loader

#### Default image loader

Library is also contains default image loader but it's not optimized for performance.

> Note: it's highly recommended to use custom image loader against default

### Configuration

You can specify a bunch of settings during gallery initialization.

|Option|Method|Description|
|------|------|-----------|
| Thumbnail size | `.setThumbnailSize(200)` | You can configure thumbnails size in |
| Zoom | `.setZoom(true)` | Enable zoom |
| Hide thumbnails | `.withHiddenThumbnails(false)` | Hide scroll view container with thumbnails on the bottom of screen |
| Hide thumbnails on click | `.hideThumbnailsOnClick(true)` | Hide scroll view container with thumbnails when you click on main image area |

## Sample application

The sample application published on Google Play.

[![Get it on Google Play](http://www.android.com/images/brand/get_it_on_play_logo_small.png)](https://play.google.com/store/apps/details?id=com.veinhorn.scrollgalleryview)

## License

    MIT License

    Copyright (c) 2015, 2018 Boris Korogvich

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
