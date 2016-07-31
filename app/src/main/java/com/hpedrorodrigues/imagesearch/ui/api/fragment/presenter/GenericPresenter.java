package com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter;

import android.Manifest;
import android.app.DownloadManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Feature;
import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;
import com.hpedrorodrigues.imagesearch.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.view.GenericView;
import com.hpedrorodrigues.imagesearch.ui.api.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;
import com.hpedrorodrigues.imagesearch.util.StringUtil;
import com.hpedrorodrigues.imagesearch.util.general.BroadcastUtil;
import com.hpedrorodrigues.imagesearch.util.general.ClipboardUtil;
import com.hpedrorodrigues.imagesearch.util.general.DownloadUtil;
import com.hpedrorodrigues.imagesearch.util.general.FeatureUtil;
import com.hpedrorodrigues.imagesearch.util.general.ShareUtil;
import com.hpedrorodrigues.imagesearch.util.general.ToastUtil;
import com.hpedrorodrigues.imagesearch.util.rx.Rx;
import com.hpedrorodrigues.imagesearch.util.rx.SearchViewObservable;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import timber.log.Timber;

public class GenericPresenter extends BasePresenter<GenericFragment> {

    private static final int WRITE_EXTERNAL_STORAGE = 1;

    private final GenericView view;

    private final Api api;

    @Inject
    public ClipboardUtil clipboardUtil;

    @Inject
    public ShareUtil shareUtil;

    @Inject
    public DownloadUtil downloadUtil;

    @Inject
    public ToastUtil toastUtil;

    @Inject
    public BroadcastUtil broadcastUtil;

    @Inject
    public FeatureUtil featureUtil;

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        featureUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
                    Feature shareFeature = createImageFeature(image);
                    featureUtil.requestFeature(shareFeature, (feature, permissionGranted) -> {
                        if (permissionGranted) {
                            shareImage(image);
                        } else {
                            toastUtil.showLong("Permission not granted");
                        }
                    });
                    break;

                case R.id.action_share_link:
                    shareUtil.shareText(getActivity(), image.getImageUrl());
                    break;

                case R.id.action_copy_link:
                    clipboardUtil.copy(image.getImageUrl());
                    break;

                case R.id.action_download:
                    Feature downloadFeature = createImageFeature(image);
                    featureUtil.requestFeature(downloadFeature, (feature, permissionGranted) -> {
                        if (permissionGranted) {
                            downloadImage(image);
                        } else {
                            toastUtil.showLong("Permission not granted");
                        }
                    });
                    break;
            }
        });
    }

    private Feature createImageFeature(Image image) {
        Feature feature = new Feature();

        feature.setActivity(getActivity());
        feature.setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        feature.setRequestCode(WRITE_EXTERNAL_STORAGE);
        feature.setValue(image);

        return feature;
    }

    private void downloadImage(Image image) {
        long imageId = downloadUtil.enqueueDownload(image.getImageUrl(), ISConstant.DEFAULT_DIRECTORY);

        toastUtil.showLong(context.getString(R.string.downloading, image.getImageUrl()));

        broadcastUtil.register(getActivity(), DownloadManager.ACTION_DOWNLOAD_COMPLETE, (context1, intent) -> {
            if (downloadUtil.isCompleted(imageId)) {

                String path = downloadUtil.getPathById(imageId);
                String message = StringUtil.isEmpty(path)
                        ? context.getString(R.string.error_downloading_image)
                        : context.getString(R.string.image_downloaded_successful, path);

                toastUtil.showLong(message);

                return true;
            }

            return false;
        });
    }

    private void shareImage(Image image) {
        long imageId = downloadUtil.enqueueDownload(image.getImageUrl(), ISConstant.DEFAULT_DIRECTORY);

        toastUtil.showLong(context.getString(R.string.downloading, image.getImageUrl()));

        broadcastUtil.register(getActivity(), DownloadManager.ACTION_DOWNLOAD_COMPLETE, (context1, intent) -> {
            if (downloadUtil.isCompleted(imageId)) {

                String path = downloadUtil.getPathById(imageId);
                if (StringUtil.isEmpty(path)) {
                    toastUtil.showLong(context.getString(R.string.error_downloading_image));
                } else {
                    shareUtil.shareImage(getActivity(), path);
                }

                return true;
            }

            return false;
        });
    }
}