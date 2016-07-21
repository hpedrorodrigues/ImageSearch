package com.hpedrorodrigues.imagesearch.dagger.module;

import com.hpedrorodrigues.imagesearch.activity.MainActivity;

import dagger.Module;

@Module
public final class MainModule extends BaseModule<MainActivity> {

    public MainModule(MainActivity activity) {
        super(activity);
    }
}