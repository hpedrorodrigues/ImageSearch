package com.hpedrorodrigues.imagesearch.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.network.ImageApi;
import com.hpedrorodrigues.imagesearch.presenter.MainPresenter;
import com.hpedrorodrigues.imagesearch.rx.Rx;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private MainPresenter presenter;

    @Inject
    public ImageApi imageApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.onCreate(savedInstanceState);

        imageApi
                .flickrSearch("car", 15, 1)
                .compose(Rx.applySchedulers())
                .subscribe(
                        page -> Timber.i("Success: %s", String.valueOf(page)),
                        error -> Timber.e(error, "Error searching images in flickr"),
                        () -> Timber.i("Finished flickr search")
                );
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
}