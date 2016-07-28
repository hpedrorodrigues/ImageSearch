package com.hpedrorodrigues.imagesearch.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.entity.ImageEntity;
import com.hpedrorodrigues.imagesearch.network.ImageApi;
import com.hpedrorodrigues.imagesearch.network.api.street_view.StreetViewImageDetail;
import com.hpedrorodrigues.imagesearch.presenter.MainPresenter;
import com.hpedrorodrigues.imagesearch.resolver.Api;
import com.hpedrorodrigues.imagesearch.resolver.ApiResolver;
import com.hpedrorodrigues.imagesearch.rx.Rx;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private MainPresenter presenter;

    @Inject
    public ImageApi imageApi;

    @Inject
    public ApiResolver apiResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.onCreate(savedInstanceState);

        search();
    }

    @Override
    protected void setUpPresenter() {
        presenter = new MainPresenter(this);
        getComponent().inject(presenter);
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected String getScreenName() {
        return "Main";
    }

    @Override
    public void onBackPressed() {
        if (presenter.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item) && super.onOptionsItemSelected(item);
    }

    private void search() {
        imageApi
                .flickrSearch("car", 15, 1)
                .compose(Rx.applySchedulers())
                .subscribe(
                        wrapper -> {
                            List<ImageEntity> images = apiResolver.resolve(Api.FLICKR, wrapper);
                            Timber.i("Flickr Success: %s", String.valueOf(images));
                        },
                        error -> Timber.e(error, "Error searching images in Flickr"),
                        () -> Timber.i("Finished Flickr search")
                );

        imageApi
                .cseSearch("car", 1, 15)
                .compose(Rx.applySchedulers())
                .subscribe(
                        wrapper -> {
                            List<ImageEntity> images = apiResolver.resolve(Api.CSE, wrapper);
                            Timber.i("CSE Success: %s", String.valueOf(images));
                        },
                        error -> Timber.e(error, "Error searching images in CSE"),
                        () -> Timber.i("Finished CSE search")
                );

        imageApi
                .imgurSearch("car", 15, 1)
                .compose(Rx.applySchedulers())
                .subscribe(
                        wrapper -> {
                            List<ImageEntity> images = apiResolver.resolve(Api.IMGUR, wrapper);
                            Timber.i("Imgur Success: %s", String.valueOf(images));
                        },
                        error -> Timber.e(error, "Error searching images in Imgur"),
                        () -> Timber.i("Finished Imgur search")
                );

        imageApi
                .duckDuckGoSearch("car", 15, 1)
                .compose(Rx.applySchedulers())
                .subscribe(
                        wrapper -> {
                            List<ImageEntity> images = apiResolver.resolve(Api.DUCK_DUCK_GO, wrapper);
                            Timber.i("DuckDuckGo Success: %s", String.valueOf(images));
                        },
                        error -> Timber.e(error, "Error searching images in DuckDuckGo"),
                        () -> Timber.i("Finished DuckDuckGo search")
                );

        imageApi
                .bingSearch("car", 15, 1)
                .compose(Rx.applySchedulers())
                .subscribe(
                        wrapper -> {
                            List<ImageEntity> images = apiResolver.resolve(Api.BING, wrapper);
                            Timber.i("Bing Success: %s", String.valueOf(images));
                        },
                        error -> Timber.e(error, "Error searching images in Bing"),
                        () -> Timber.i("Finished Bing search")
                );

        StreetViewImageDetail imageDetail = new StreetViewImageDetail();

        imageDetail.setWidth(600);
        imageDetail.setHeight(300);
        imageDetail.setLatitude(46.414382);
        imageDetail.setLongitude(10.013988);
        imageDetail.setHeading(151.78);
        imageDetail.setPitch(-0.76);
        imageDetail.setScale(2);

        String imageUrl = imageApi.getImageUrl(imageDetail);
        Timber.i("StreetView image Url: %s", imageUrl);
    }
}