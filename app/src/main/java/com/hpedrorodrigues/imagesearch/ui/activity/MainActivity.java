package com.hpedrorodrigues.imagesearch.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.network.services.street_view.StreetViewImageDetail;
import com.hpedrorodrigues.imagesearch.api.service.GenericService;
import com.hpedrorodrigues.imagesearch.ui.api.activity.presenter.MainPresenter;
import com.hpedrorodrigues.imagesearch.util.rx.Rx;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @Inject
    public GenericService genericService;

    private MainPresenter presenter;

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
        genericService
                .searchAll("car", 1, 15, false)
                .compose(Rx.applySchedulers())
                .subscribe(
                        images -> Timber.i("All Success: %s", String.valueOf(images)),
                        error -> Timber.e(error, "Error searching images in all apis"),
                        () -> Timber.i("Finished all search")
                );

        StreetViewImageDetail imageDetail = new StreetViewImageDetail();

        imageDetail.setWidth(600);
        imageDetail.setHeight(300);
        imageDetail.setLatitude(46.414382);
        imageDetail.setLongitude(10.013988);
        imageDetail.setHeading(151.78);
        imageDetail.setPitch(-0.76);
        imageDetail.setScale(2);

        String imageUrl = genericService.getImageUrl(imageDetail);
        Timber.i("StreetView image Url: %s", imageUrl);
    }
}