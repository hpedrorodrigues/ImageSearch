package com.hpedrorodrigues.imagesearch.dagger.component;

import com.hpedrorodrigues.imagesearch.activity.BaseActivity;
import com.hpedrorodrigues.imagesearch.activity.MainActivity;
import com.hpedrorodrigues.imagesearch.dagger.module.ISModule;
import com.hpedrorodrigues.imagesearch.fragment.BaseFragment;
import com.hpedrorodrigues.imagesearch.presenter.MainPresenter;

import dagger.Component;

@Component(modules = ISModule.class)
public interface ISComponent extends BaseComponent {

    void inject(BaseActivity activity);

    void inject(MainActivity activity);

    void inject(MainPresenter presenter);

    void inject(BaseFragment fragment);
}