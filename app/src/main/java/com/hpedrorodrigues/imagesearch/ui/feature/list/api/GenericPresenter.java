package com.hpedrorodrigues.imagesearch.ui.feature.list.api;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.data.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.data.constant.IntentKey;
import com.hpedrorodrigues.imagesearch.data.constant.PreferenceKey;
import com.hpedrorodrigues.imagesearch.data.manager.ImageActionManager;
import com.hpedrorodrigues.imagesearch.data.model.Image;
import com.hpedrorodrigues.imagesearch.data.receiver.observable.NetworkStateObservable;
import com.hpedrorodrigues.imagesearch.data.remote.api.Api;
import com.hpedrorodrigues.imagesearch.data.service.ConnectionService;
import com.hpedrorodrigues.imagesearch.data.transformer.Rx;
import com.hpedrorodrigues.imagesearch.ui.base.BaseFragmentPresenter;
import com.hpedrorodrigues.imagesearch.ui.common.component.OnLoadMoreListener;
import com.hpedrorodrigues.imagesearch.ui.common.component.SearchViewObservable;
import com.hpedrorodrigues.imagesearch.ui.common.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.feature.image.ImageActivity;
import com.hpedrorodrigues.imagesearch.ui.feature.image.ImageAdapter;
import com.hpedrorodrigues.imagesearch.ui.feature.settings.SettingsActivity;
import com.hpedrorodrigues.imagesearch.util.general.ApiUtil;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import timber.log.Timber;

public class GenericPresenter extends BaseFragmentPresenter<GenericFragment> {

    private final GenericFragmentView view;

    private final Api api;

    @Inject
    public ImageActionManager imageActionManager;

    @Inject
    public NetworkStateObservable observable;

    @Inject
    public ConnectionService connection;

    @Inject
    public ApiUtil apiUtil;

    private String currentSearch = ISConstant.DEFAULT_SEARCH;
    private int currentPage = ISConstant.INITIAL_PAGE;

    private Subscription searchSubscription;

    private Observer observer = new Observer() {

        @Override
        public void update(Observable o, Object arg) {
            reloadNetworkView();
        }
    };

    public GenericPresenter(GenericFragment fragment, Navigator navigator, Api api) {
        super(fragment, navigator);
        this.api = api;
        this.view = new GenericFragmentView(fragment);

        getActivity().getComponent().inject(view);
    }

    @Override
    public void onViewCreated(View view) {
        this.view.onView(view);

        this.view.setUpGridView(new OnLoadMoreListener.OnMoreListener() {

            @Override
            public void onMore() {
                currentPage++;
                search(currentSearch, true);
            }
        });

        search(ISConstant.DEFAULT_SEARCH, false);
        loadTitleByApi();
        setUpImageAdapter();
        reloadNetworkView();

        this.view.setOnImageClickListener(new ImageAdapter.OnImageClickListener() {

            @Override
            public void onClick(Image image) {
                Bundle arguments = new Bundle();
                arguments.putSerializable(IntentKey.IMAGE, image);

                navigator.toActivityScreen(ImageActivity.class, arguments);
            }
        });

        observable.addObserver(observer);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.generic_item, menu);
        SearchView searchView = view.getSearchView(menu);

        Subscription subscription = new SearchViewObservable(searchView)
                .create()
                .subscribe(new Action1<String>() {

                               @Override
                               public void call(String query) {
                                   view.clearImageAdapter();

                                   currentPage = ISConstant.INITIAL_PAGE;

                                   currentSearch = query;

                                   search(query, false);

                                   Timber.d("Searching for query: %s", query);
                               }
                           },
                        new Action1<Throwable>() {

                            @Override
                            public void call(Throwable error) {
                                Timber.e(error, "Error searching images");
                            }
                        }
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

        eventTracker.trackSearch(api, query, currentPage);

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
        this.view.setUpImageAdapter(new ImageAdapter.OnPopupItemClickListener() {

            @Override
            public void onClick(MenuItem item, Image image) {
                switch (item.getItemId()) {
                    case R.id.action_share:
                        imageActionManager.shareImage(image, getActivity());
                        break;
                    case R.id.action_share_link:
                        imageActionManager.shareImageUrl(image, getActivity());
                        break;
                    case R.id.action_copy_link:
                        imageActionManager.copyImageUrl(image);
                        break;
                    case R.id.action_download:
                        imageActionManager.downloadImage(image, getActivity());
                        break;
                    case R.id.action_set_as_wallpaper:
                        imageActionManager.changeWallpaper(image, getActivity());
                        break;
                }
            }
        });
    }

    private void searchAll(String query, boolean safeSearch) {
        searchSubscription = genericService
                .searchAll(query, currentPage, ISConstant.IMAGES_PER_PAGE, safeSearch)
                .compose(Rx.<List<Image>>applySchedulers())
                .subscribe(
                        new Action1<List<Image>>() {
                            @Override
                            public void call(final List<Image> images) {
                                getActivity().runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        view.hideProgress();
                                        view.addContentToGridView(images);
                                        view.showSmallProgress();

                                        Timber.d("Images loaded %s", images);
                                    }
                                });
                            }
                        },
                        new Action1<Throwable>() {

                            @Override
                            public void call(Throwable error) {
                                Timber.e(error, "Error loading images");
                            }
                        },
                        new Action0() {

                            @Override
                            public void call() {
                                view.enableLoadMore();
                                view.hideSmallProgress();

                                if (view.isEmpty()) {
                                    view.showNoResultsView();
                                } else {
                                    view.hideNoResultsView();
                                }
                            }
                        }
                );

        bindSubscription(searchSubscription);
    }

    private void searchByApi(String query, final boolean smallLoading, boolean safeSearch) {
        searchSubscription = genericService
                .search(api, query, currentPage, ISConstant.IMAGES_PER_PAGE, safeSearch)
                .compose(Rx.<Map>applySchedulers())
                .subscribe(
                        new Action1<Map>() {

                            @Override
                            public void call(Map data) {
                                final List<Image> images = genericService.parse(api, data);
                                getActivity().runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        view.addContentToGridView(images);
                                        view.enableLoadMore();

                                        if (smallLoading) {
                                            view.hideSmallProgress();
                                        } else {
                                            view.hideProgress();
                                        }

                                        Timber.d("Images loaded %s", images);
                                    }
                                });
                            }
                        },
                        new Action1<Throwable>() {

                            @Override
                            public void call(Throwable error) {
                                Timber.e(error, "Error loading images");
                            }
                        },
                        new Action0() {

                            @Override
                            public void call() {
                                if (view.isEmpty()) {
                                    view.showNoResultsView();
                                } else {
                                    view.hideNoResultsView();
                                }
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