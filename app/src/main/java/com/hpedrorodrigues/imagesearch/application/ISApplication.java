package com.hpedrorodrigues.imagesearch.application;

import android.app.Application;

import com.hpedrorodrigues.imagesearch.dagger.component.DaggerISComponent;
import com.hpedrorodrigues.imagesearch.dagger.component.ISComponent;
import com.hpedrorodrigues.imagesearch.dagger.module.ISModule;

public class ISApplication extends Application {

    private ISComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerISComponent.builder().iSModule(new ISModule()).build();
    }

    public ISComponent getComponent() {
        return component;
    }
}