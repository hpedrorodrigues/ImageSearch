package com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter;

import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;
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
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.generic_item, menu);
        SearchView searchView = view.getSearchView(menu);

        Subscription subscription = new SearchViewObservable(searchView)
                .create()
                .subscribe(
                        query -> {
                            setUp(query);
                            answer.logSearch(api, query);
                            Timber.d("Searching for query: %s", query);
                        },
                        error -> Timber.e(error, "Error searching images")
                );

        bindSubscription(subscription);
    }

    public void setUp(String search) {
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