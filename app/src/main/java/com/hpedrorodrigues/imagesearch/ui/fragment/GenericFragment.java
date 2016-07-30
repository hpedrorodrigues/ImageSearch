package com.hpedrorodrigues.imagesearch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;
import com.hpedrorodrigues.imagesearch.constant.BundleKey;
import com.hpedrorodrigues.imagesearch.ui.activity.MainActivity;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter.GenericPresenter;

public class GenericFragment extends BaseFragment {

    private GenericPresenter genericPresenter;

    public static GenericFragment create(Api api) {
        GenericFragment fragment = new GenericFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(BundleKey.API, api);

        fragment.setArguments(arguments);

        return fragment;
    }

    public Api getApi() {
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

        genericPresenter.onViewCreated(view);
    }

    @Override
    protected void setUpPresenter() {
        MainActivity activity = (MainActivity) getActivity();
        genericPresenter = new GenericPresenter(this, activity.getNavigator(), getApi());
        getComponent().inject(genericPresenter);
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected String getScreenName() {
        return getApi().name();
    }
}