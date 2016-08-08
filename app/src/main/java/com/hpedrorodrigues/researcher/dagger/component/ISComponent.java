package com.hpedrorodrigues.researcher.dagger.component;

import com.hpedrorodrigues.researcher.api.network.api.BaseApi;
import com.hpedrorodrigues.researcher.api.parser.BaseParser;
import com.hpedrorodrigues.researcher.component.receiver.NetworkStateChangedReceiver;
import com.hpedrorodrigues.researcher.dagger.module.ISModule;
import com.hpedrorodrigues.researcher.ui.activity.ImageActivity;
import com.hpedrorodrigues.researcher.ui.activity.MainActivity;
import com.hpedrorodrigues.researcher.ui.activity.SettingsActivity;
import com.hpedrorodrigues.researcher.ui.api.activity.presenter.ImagePresenter;
import com.hpedrorodrigues.researcher.ui.api.activity.presenter.MainPresenter;
import com.hpedrorodrigues.researcher.ui.api.activity.presenter.SettingsPresenter;
import com.hpedrorodrigues.researcher.ui.api.activity.view.MainView;
import com.hpedrorodrigues.researcher.ui.api.fragment.presenter.GenericPresenter;
import com.hpedrorodrigues.researcher.ui.api.fragment.view.GenericView;
import com.hpedrorodrigues.researcher.ui.component.ImageDetailDialog;
import com.hpedrorodrigues.researcher.ui.fragment.GenericFragment;

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