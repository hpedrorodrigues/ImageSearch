package com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter;

import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;
import com.hpedrorodrigues.imagesearch.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.view.GenericView;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;
import com.hpedrorodrigues.imagesearch.util.general.ClipboardUtil;
import com.hpedrorodrigues.imagesearch.util.general.DownloadUtil;
import com.hpedrorodrigues.imagesearch.util.general.ShareUtil;
import com.hpedrorodrigues.imagesearch.util.rx.Rx;
import com.hpedrorodrigues.imagesearch.util.rx.SearchViewObservable;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import timber.log.Timber;

public class GenericPresenter extends BasePresenter<GenericFragment> {

    private final GenericView view;

    private final Api api;

    @Inject
    public ClipboardUtil clipboardUtil;

    @Inject
    public ShareUtil shareUtil;

    @Inject
    public DownloadUtil downloadUtil;

    public GenericPresenter(GenericFragment fragment, Navigator navigator, Api api) {
        super(fragment, navigator);
        this.api = api;
        this.view = new GenericView(fragment);

        getActivity().getComponent().inject(view);
    }

    @Override
    public void onViewCreated(View view) {
        this.view.onView(view);

        search(ISConstant.DEFAULT_SEARCH);
        loadTitleByApi();
        setUpImageAdapter();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.generic_item, menu);
        SearchView searchView = view.getSearchView(menu);

        Subscription subscription = new SearchViewObservable(searchView)
                .create()
                .subscribe(
                        query -> {
                            search(query);
                            answer.logSearch(api, query);
                            Timber.d("Searching for query: %s", query);
                        },
                        error -> Timber.e(error, "Error searching images")
                );

        bindSubscription(subscription);
    }

    private void loadTitleByApi() {
        String title;

        if (api == null) {

            title = context.getString(R.string.all_provider);
        } else if (api.equals(Api.FLICKR)) {

            title = context.getString(R.string.flickr_provider);
        } else if (api.equals(Api.CSE)) {

            title = context.getString(R.string.google_provider);
        } else if (api.equals(Api.IMGUR)) {

            title = context.getString(R.string.imgur_provider);
        } else if (api.equals(Api.DUCK_DUCK_GO)) {

            title = context.getString(R.string.duck_duck_go_provider);
        } else if (api.equals(Api.BING)) {

            title = context.getString(R.string.bing_provider);
        } else if (api.equals(Api.PIXABAY)) {

            title = context.getString(R.string.pixabay_provider);
        } else {

            title = context.getString(R.string.giphy_provider);
        }

        fragment.getToolbar().setTitle(title);
    }

    public void search(String search) {
        view.showProgress();
        view.clearImageAdapter();

        if (api == null) {

            genericService
                    .searchAll(search, 1, 15, false)
                    .compose(Rx.applySchedulers())
                    .subscribe(
                            images -> {
                                view.setContentFromGridView(images);
                                view.hideProgress();
                                Timber.i("Images loaded %s", images);
                            },
                            error -> Timber.e(error, "Error")
                    );
        } else {

            genericService
                    .search(api, search, 1, 15, false)
                    .compose(Rx.applySchedulers())
                    .subscribe(
                            data -> {
                                List<Image> images = genericService.parse(api, data);
                                view.setContentFromGridView(images);
                                view.hideProgress();
                                Timber.i("Images loaded %s", images);
                            },
                            error -> Timber.e(error, "Error")
                    );
        }
    }

    private void setUpImageAdapter() {
        this.view.setUpImageAdapter((item, image) -> {
            switch (item.getItemId()) {

                case R.id.action_share:
                    long imageId = downloadUtil
                            .enqueueDownload(image.getImageUrl(), ISConstant.DEFAULT_DIRECTORY);

                    // TODO: this must be made in DownloadCompletedReceiver
                    String path = downloadUtil.getPathById(imageId);
                    shareUtil.shareImage(getActivity(), path);
                    break;

                case R.id.action_share_link:
                    shareUtil.shareText(getActivity(), image.getImageUrl());
                    break;

                case R.id.action_copy_link:
                    clipboardUtil.copy(image.getImageUrl());
                    break;

                case R.id.action_download:
                    downloadUtil.enqueueDownload(image.getImageUrl(), ISConstant.DEFAULT_DIRECTORY);
                    Toast.makeText(
                            context,
                            context.getString(R.string.downloading, image.getImageUrl()),
                            Toast.LENGTH_LONG
                    ).show();
                    break;
            }
        });
    }
}