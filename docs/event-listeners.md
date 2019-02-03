# Event Listeners

You can add event listeners to catch events like:

- image click event

- long image click event

- page change event

  

## Click Listener

You can add on image click listener using [GalleryBuilder](https://github.com/VEINHORN/ScrollGalleryView/blob/dev/library/src/main/java/com/veinhorn/scrollgalleryview/builder/GalleryBuilder.java) (**recommended approach**):

```java
.onImageClickListener(new ScrollGalleryView.OnImageClickListener() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(MainActivity.this, "image position = " + position, Toast.LENGTH_SHORT).show();
                    }
                })
```

Or add event listener right to the *ScrollGalleryView*:

```java
galleryView.addOnImageClickListener(new ScrollGalleryView.OnImageClickListener() {
            @Override
            public void onClick(int position) {
                
            }
        });
```

You should not add listeners directly to the *ScrollGalleryView* cause you can face with initialization bug. More details [here](https://github.com/VEINHORN/ScrollGalleryView/issues/44#issuecomment-362689217).

## Long Click Listener

Add long click listener with [GalleryBuilder](https://github.com/VEINHORN/ScrollGalleryView/blob/dev/library/src/main/java/com/veinhorn/scrollgalleryview/builder/GalleryBuilder.java) (**recommended approach**):

```java
.onImageLongClickListener(new ScrollGalleryView.OnImageLongClickListener() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(MainActivity.this, "image position = " + position, Toast.LENGTH_SHORT).show();
                    }
                })
```

Or add it to the *ScrollGalleryView*:

```java
galleryView.addOnImageLongClickListener(new ScrollGalleryView.OnImageLongClickListener() {
            @Override
            public void onClick(int position) {
                
            }
        });
```

## Page Change Listener

For now library supports only adding this listener directly to the *ScrollGalleryView*:

```java
galleryView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
```

