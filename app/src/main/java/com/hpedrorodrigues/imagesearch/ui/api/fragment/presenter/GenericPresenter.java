package com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;
import com.hpedrorodrigues.imagesearch.component.receiver.observable.NetworkStateObservable;
import com.hpedrorodrigues.imagesearch.component.service.ConnectionService;
import com.hpedrorodrigues.imagesearch.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.constant.IntentKey;
import com.hpedrorodrigues.imagesearch.constant.PreferenceKey;
import com.hpedrorodrigues.imagesearch.ui.activity.ImageActivity;
import com.hpedrorodrigues.imagesearch.ui.activity.SettingsActivity;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.view.GenericView;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;
import com.hpedrorodrigues.imagesearch.util.general.ApiUtil;
import com.hpedrorodrigues.imagesearch.util.general.ImageActionUtil;
import com.hpedrorodrigues.imagesearch.util.rx.Rx;
import com.hpedrorodrigues.imagesearch.util.rx.SearchViewObservable;

import java.util.List;
import java.util.Observer;

import javax.inject.Inject;

import rx.Subscription;
import timber.log.Timber;

public class GenericPresenter extends BasePresenter<GenericFragment> {

    private final GenericView view;

    private final Api api;

    @Inject
    public ImageActionUtil imageActionUtil;

    @Inject
    public NetworkStateObservable observable;

    @Inject
    public ConnectionService connection;

    @Inject
    public ApiUtil apiUtil;

    private String currentSearch = ISConstant.DEFAULT_SEARCH;
    private int currentPage = ISConstant.INITIAL_PAGE;

    private Subscription searchSubscription;

    private Observer observer = (observable, data) -> reloadNetworkView();

    public GenericPresenter(GenericFragment fragment, Navigator navigator, Api api) {
        super(fragment, navigator);
        this.api = api;
        this.view = new GenericView(fragment);

        getActivity().getComponent().inject(view);
    }

    @Override
    public void onViewCreated(View view) {
        this.view.onView(view);

        this.view.setUpGridView(() -> {
            currentPage++;
            search(currentSearch, true);
        });

        search(ISConstant.DEFAULT_SEARCH, false);
        loadTitleByApi();
        setUpImageAdapter();
        reloadNetworkView();

        this.view.setOnImageClickListener(image -> {
            Bundle arguments = new Bundle();
            arguments.putSerializable(IntentKey.IMAGE, image);

            navigator.toActivityScreen(ImageActivity.class, arguments);
        });

        observable.addObserver(observer);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.generic_item, menu);
        SearchView searchView = view.getSearchView(menu);

        Subscription subscription = new SearchViewObservable(searchView)
                .create()
                .subscribe(
                        query -> {
                            view.clearImageAdapter();

                            currentPage = ISConstant.INITIAL_PAGE;

                            currentSearch = query;

                            search(query, false);

                            Timber.d("Searching for query: %s", query);
                        },
                        error -> Timber.e(error, "Error searching images")
                );

        bindSubscription(subscription);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            navigator.toActivityScreen(SettingsActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        imageActionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onResume() {
        super.onResume();

        boolean showImagesDescription = preferences
                .getBoolean(PreferenceKey.SHOW_IMAGES_DESCRIPTION, ISConstant.DEFAULT_SHOW_IMAGES_DESCRIPTION);
        this.view.setShowImagesDescription(showImagesDescription);
    }

    @Override
    public void cancelPendingProcesses() {
        super.cancelPendingProcesses();

        observable.deleteObserver(observer);
    }

    private void loadTitleByApi() {
        String title = context.getString(apiUtil.getProviderNameByApi(api));
        fragment.getToolbar().setTitle(title);
    }

    public void search(String query, boolean smallLoading) {
        if (smallLoading) {
            view.showSmallProgress();
        } else {
            view.showProgress();
        }

        answer.logSearch(api, query, currentPage);

        cancelOldSearchSubscription();

        boolean safeSearch = preferences
                .getBoolean(PreferenceKey.SAFE_SEARCH, ISConstant.DEFAULT_SAFE_SEARCH);

        if (api == null) {
            searchAll(query, safeSearch);
        } else {
            searchByApi(query, smallLoading, safeSearch);
        }
    }

    private void setUpImageAdapter() {
        this.view.setUpImageAdapter((item, image) -> {
            switch (item.getItemId()) {
                case R.id.action_share:
                    imageActionUtil.shareImage(image, getActivity());
                    break;
                case R.id.action_share_link:
                    imageActionUtil.shareImageUrl(image, getActivity());
                    break;
                case R.id.action_copy_link:
                    imageActionUtil.copyImageUrl(image);
                    break;
                case R.id.action_download:
                    imageActionUtil.downloadImage(image, getActivity());
                    break;
                case R.id.action_set_as_wallpaper:
                    imageActionUtil.changeWallpaper(image, getActivity());
                    break;
            }
        });
    }

    private void searchAll(String query, boolean safeSearch) {
        searchSubscription = genericService
                .searchAll(query, currentPage, ISConstant.IMAGES_PER_PAGE, safeSearch)
                .compose(Rx.applySchedulers())
                .subscribe(
                        images -> getActivity().runOnUiThread(() -> {

                            view.hideProgress();
                            view.addContentToGridView(images);
                            view.showSmallProgress();

                            Timber.d("Images loaded %s", images);
                        }),
                        error -> Timber.e(error, "Error loading images"),
                        () -> {
                            view.enableLoadMore();
                            view.hideSmallProgress();

                            if (view.isEmpty()) {
                                view.showNoResultsView();
                            } else {
                                view.hideNoResultsView();
                            }
                        }
                );

        bindSubscription(searchSubscription);
    }

    private void searchByApi(String query, boolean smallLoading, boolean safeSearch) {
        searchSubscription = genericService
                .search(api, query, currentPage, ISConstant.IMAGES_PER_PAGE, safeSearch)
                .compose(Rx.applySchedulers())
                .subscribe(
                        data -> {
                            List<Image> images = genericService.parse(api, data);
                            getActivity().runOnUiThread(() -> {

                                view.addContentToGridView(images);
                                view.enableLoadMore();

                                if (smallLoading) {
                                    view.hideSmallProgress();
                                } else {
                                    view.hideProgress();
                                }

                                Timber.d("Images loaded %s", images);
                            });
                        },
                        error -> Timber.e(error, "Error loading images"),
                        () -> {
                            if (view.isEmpty()) {
                                view.showNoResultsView();
                            } else {
                                view.hideNoResultsView();
                            }
                        }
                );

        bindSubscription(searchSubscription);
    }

    private void cancelOldSearchSubscription() {
        if (searchSubscription != null && !searchSubscription.isUnsubscribed()) {
            Timber.d("Cancelling old search subscription");

            searchSubscription.unsubscribe();
            searchSubscription = null;
        }
    }

    private void reloadNetworkView() {
        if (connection.hasConnection()) {
            view.hideWithoutNetwork();
        } else {
            view.showWithoutNetwork();
        }
    }
}