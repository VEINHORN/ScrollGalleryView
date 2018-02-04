package com.veinhorn.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final ArrayList<String> imageUrls = new ArrayList<>(Arrays.asList(
            "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg",
            "http://www.personal.psu.edu/pms5279/JPG.jpg",
            "http://img1.goodfon.ru/original/1920x1080/d/f5/aircraft-jet-su-47-berkut.jpg",
            "http://www.dishmodels.ru/picture/glr/13/13312/g13312_7657277.jpg",
            "http://img2.goodfon.ru/original/1920x1080/b/c9/su-47-berkut-c-37-firkin.jpg"
    ));
    private static final String movieUrl = "http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4";

    @BindView(R.id.scroll_gallery_view)
    protected ScrollGalleryView scrollGalleryView;

    @BindView(R.id.spin_kit)
    protected SpinKitView progressBar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<MediaInfo> infos = new ArrayList<>(imageUrls.size());
        for (String url : imageUrls) infos.add(MediaInfo.mediaLoader(new PicassoImageLoader(url)));

        scrollGalleryView
                .setThumbnailSize(200)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager());

        Wave wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);

        ImagesFetcher fetcher = new ImagesFetcher();
        fetcher.execute();
    }

    private class ImagesFetcher extends AsyncTask<Void, Void, List<String>> {
        private static final String SERVER_URL = "https://www.freeimages.com/";
        public static final int IMAGES_COUNT = 6;

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
            List<String> images = new ArrayList<String>();
            int counter = 0;
            for (Element imageElm : imageElms) {
                images.add(imageElm.attr("src").replace("small-previews", "large-previews"));
                if (++counter == size) break;
            }
            return images;
        }
    }
}
