package com.veinhorn.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Wave;
import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;
import com.veinhorn.scrollgalleryview.loader.picasso.PicassoImageLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected ScrollGalleryView scrollGalleryView;
    protected SpinKitView progressBar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollGalleryView = findViewById(R.id.scroll_gallery_view);
        progressBar = findViewById(R.id.spin_kit);

        scrollGalleryView
                .setThumbnailSize(200)
                .setZoom(true)
                .withHiddenThumbnails(false)
                .hideThumbnailsOnClick(true)
                .hideThumbnailsAfter(5000)
                .addOnImageClickListener((position) -> {
                    Log.i(getClass().getName(), "You have clicked on image #" + position);
                })
                .setFragmentManager(getSupportFragmentManager());

        Wave wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);

        ImagesFetcher fetcher = new ImagesFetcher();
        fetcher.execute();
    }

    private class ImagesFetcher extends AsyncTask<Void, Void, List<String>> {
        private static final String SERVER_URL = "https://www.freeimages.com/";
        private static final int IMAGES_COUNT = 6;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                return getImageUrls(getImageElements(), IMAGES_COUNT);
            } catch (IOException e) {
                Log.e(getClass().getName(), "Cannot load image urls", e);
            }
            return Collections.emptyList();
        }

        @Override
        protected void onPostExecute(List<String> imageUrls) {
            progressBar.setVisibility(View.INVISIBLE);

            for (String imageUrl : imageUrls) {
                scrollGalleryView.addMedia(
                        MediaInfo.mediaLoader(new PicassoImageLoader(imageUrl))
                );
            }
        }

        public Elements getImageElements() throws IOException {
            return Jsoup.connect(SERVER_URL)
                    .get()
                    .getElementById("content")
                    .getElementsByTag("img");
        }

        public List<String> getImageUrls(Elements imageElms, int size) {
            List<String> images = new ArrayList<>();
            int counter = 0;
            for (Element imageElm : imageElms) {
                images.add(imageElm.attr("src")); // .replace("small-previews", "large-previews"));
                if (++counter == size) break;
            }
            return images;
        }
    }
}
