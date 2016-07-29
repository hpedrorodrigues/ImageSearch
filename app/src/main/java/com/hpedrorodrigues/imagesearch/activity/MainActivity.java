package com.hpedrorodrigues.imagesearch.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.entity.Image;
import com.hpedrorodrigues.imagesearch.network.api.Api;
import com.hpedrorodrigues.imagesearch.network.api.GenericApi;
import com.hpedrorodrigues.imagesearch.network.services.street_view.StreetViewImageDetail;
import com.hpedrorodrigues.imagesearch.parser.GenericParser;
import com.hpedrorodrigues.imagesearch.presenter.MainPresenter;
import com.hpedrorodrigues.imagesearch.rx.Rx;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private MainPresenter presenter;

    @Inject
    public GenericApi genericApi;

    @Inject
    public GenericParser genericParser;

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
        genericApi
                .search(Api.FLICKR, "car", 1, 15, false)
                .compose(Rx.applySchedulers())
                .subscribe(
                        data -> {
                            List<Image> images = genericParser.parse(Api.FLICKR, data);
                            Timber.i("Flickr Success: %s", String.valueOf(images));
                        },
                        error -> Timber.e(error, "Error searching images in Flickr"),
                        () -> Timber.i("Finished Flickr search")
                );

        genericApi
                .search(Api.CSE, "car", 1, 15, false)
                .compose(Rx.applySchedulers())
                .subscribe(
                        data -> {
                            List<Image> images = genericParser.parse(Api.CSE, data);
                            Timber.i("CSE Success: %s", String.valueOf(images));
                        },
                        error -> Timber.e(error, "Error searching images in CSE"),
                        () -> Timber.i("Finished CSE search")
                );

        genericApi
                .search(Api.IMGUR, "car", 1, 15, false)
                .compose(Rx.applySchedulers())
                .subscribe(
                        data -> {
                            List<Image> images = genericParser.parse(Api.IMGUR, data);
                            Timber.i("Imgur Success: %s", String.valueOf(images));
                        },
                        error -> Timber.e(error, "Error searching images in Imgur"),
                        () -> Timber.i("Finished Imgur search")
                );

        genericApi
                .search(Api.DUCK_DUCK_GO, "car", 1, 15, false)
                .compose(Rx.applySchedulers())
                .subscribe(
                        data -> {
                            List<Image> images = genericParser.parse(Api.DUCK_DUCK_GO, data);
                            Timber.i("DuckDuckGo Success: %s", String.valueOf(images));
                        },
                        error -> Timber.e(error, "Error searching images in DuckDuckGo"),
                        () -> Timber.i("Finished DuckDuckGo search")
                );

        genericApi
                .search(Api.BING, "car", 1, 15, false)
                .compose(Rx.applySchedulers())
                .subscribe(
                        data -> {
                            List<Image> images = genericParser.parse(Api.BING, data);
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

        String imageUrl = genericApi.getImageUrl(imageDetail);
        Timber.i("StreetView image Url: %s", imageUrl);
    }
}