package com.hpedrorodrigues.imagesearch.dagger.component;

import com.hpedrorodrigues.imagesearch.api.network.api.BaseApi;
import com.hpedrorodrigues.imagesearch.api.parser.BaseParser;
import com.hpedrorodrigues.imagesearch.dagger.module.ISModule;
import com.hpedrorodrigues.imagesearch.ui.activity.AboutActivity;
import com.hpedrorodrigues.imagesearch.ui.activity.MainActivity;
import com.hpedrorodrigues.imagesearch.ui.activity.SettingsActivity;
import com.hpedrorodrigues.imagesearch.ui.api.activity.presenter.AboutPresenter;
import com.hpedrorodrigues.imagesearch.ui.api.activity.presenter.MainPresenter;
import com.hpedrorodrigues.imagesearch.ui.api.activity.presenter.SettingsPresenter;
import com.hpedrorodrigues.imagesearch.ui.api.activity.view.MainView;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter.GenericPresenter;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.view.GenericView;
import com.hpedrorodrigues.imagesearch.ui.fragment.GenericFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ISModule.class)
public interface ISComponent extends BaseComponent {

    void inject(BaseApi api);

    void inject(BaseParser parser);

    void inject(MainActivity activity);

    void inject(MainPresenter presenter);

    void inject(GenericFragment fragment);

    void inject(GenericPresenter presenter);

    void inject(GenericView view);

    void inject(MainView view);

    void inject(SettingsActivity activity);

    void inject(AboutActivity activity);

    void inject(SettingsPresenter presenter);

    void inject(AboutPresenter presenter);
}