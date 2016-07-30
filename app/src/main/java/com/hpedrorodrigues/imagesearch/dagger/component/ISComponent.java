package com.hpedrorodrigues.imagesearch.dagger.component;

import com.hpedrorodrigues.imagesearch.api.network.api.BaseApi;
import com.hpedrorodrigues.imagesearch.api.parser.BaseParser;
import com.hpedrorodrigues.imagesearch.dagger.module.ISModule;
import com.hpedrorodrigues.imagesearch.ui.activity.MainActivity;
import com.hpedrorodrigues.imagesearch.ui.api.activity.presenter.MainPresenter;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;

import dagger.Component;

@Component(modules = ISModule.class)
public interface ISComponent extends BaseComponent {

    void inject(BaseApi baseApi);

    void inject(BaseParser baseParser);

    void inject(MainActivity activity);

    void inject(MainPresenter presenter);

    void inject(GenericFragment genericFragment);
}