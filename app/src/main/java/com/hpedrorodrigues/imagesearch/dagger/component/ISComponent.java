package com.hpedrorodrigues.imagesearch.dagger.component;

import com.hpedrorodrigues.imagesearch.api.network.api.BaseApi;
import com.hpedrorodrigues.imagesearch.api.parser.BaseParser;
import com.hpedrorodrigues.imagesearch.component.receiver.NetworkStateChangedReceiver;
import com.hpedrorodrigues.imagesearch.dagger.module.ISModule;
import com.hpedrorodrigues.imagesearch.ui.api.activity.view.MainView;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.presenter.GenericPresenter;
import com.hpedrorodrigues.imagesearch.ui.api.fragment.view.GenericView;
import com.hpedrorodrigues.imagesearch.ui.component.ImageDetailDialog;
import com.hpedrorodrigues.imagesearch.ui.feature.image.ImageActivity;
import com.hpedrorodrigues.imagesearch.ui.feature.image.ImagePresenter;
import com.hpedrorodrigues.imagesearch.ui.feature.list.GenericFragment;
import com.hpedrorodrigues.imagesearch.ui.feature.list.MainActivity;
import com.hpedrorodrigues.imagesearch.ui.feature.list.MainPresenter;
import com.hpedrorodrigues.imagesearch.ui.feature.settings.SettingsActivity;
import com.hpedrorodrigues.imagesearch.ui.feature.settings.SettingsPresenter;

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

    void inject(SettingsPresenter presenter);

    void inject(NetworkStateChangedReceiver receiver);

    void inject(ImageActivity activity);

    void inject(ImagePresenter presenter);

    void inject(ImageDetailDialog dialog);
}