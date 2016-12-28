package com.hpedrorodrigues.imagesearch.ui.feature.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;
import com.hpedrorodrigues.imagesearch.constant.BundleKey;
import com.hpedrorodrigues.imagesearch.ui.base.BaseFragment;

public class GenericFragment extends BaseFragment {

    private GenericPresenter presenter;

    public static GenericFragment create() {
        return new GenericFragment();
    }

    public static GenericFragment create(Api api) {
        GenericFragment fragment = new GenericFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(BundleKey.API, api);

        fragment.setArguments(arguments);

        return fragment;
    }

    public Api getApi() {
        if (getArguments() == null) {
            return null;
        }

        return (Api) getArguments().getSerializable(BundleKey.API);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.generic_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.onViewCreated(view);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        presenter.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item) && super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.onResume();
    }

    @Override
    protected void setUpPresenter() {
        MainActivity activity = (MainActivity) getActivity();
        presenter = new GenericPresenter(this, activity.getNavigator(), getApi());
        getComponent().inject(presenter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected String getScreenName() {
        if (getApi() == null) {
            return "ALL";
        }

        return getApi().name();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.cancelPendingProcesses();
    }
}