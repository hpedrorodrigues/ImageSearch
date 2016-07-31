package com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter;

import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;
import com.hpedrorodrigues.imagesearch.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.view.GenericView;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;
import com.hpedrorodrigues.imagesearch.util.rx.Rx;
import com.hpedrorodrigues.imagesearch.util.rx.SearchViewObservable;

import java.util.List;

import rx.Subscription;
import timber.log.Timber;

public class GenericPresenter extends BasePresenter<GenericFragment> {

    private final GenericView view;

    private final Api api;

    public GenericPresenter(GenericFragment fragment, Navigator navigator, Api api) {
        super(fragment, navigator);
        this.api = api;
        this.view = new GenericView(fragment);

        getActivity().getComponent().inject(view);
    }

    @Override
    public void onViewCreated(View view) {
        this.view.onView(view);
        this.view.setUpImageAdapter();

        loadTitleByApi();
        search(ISConstant.DEFAULT_SEARCH);
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
        if (api == null) {

            genericService
                    .searchAll(search, 1, 15, false)
                    .compose(Rx.applySchedulers())
                    .subscribe(
                            images -> {
                                view.setContentFromGridView(images);
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
                                Timber.i("Images loaded %s", images);
                            },
                            error -> Timber.e(error, "Error")
                    );
        }
    }
}