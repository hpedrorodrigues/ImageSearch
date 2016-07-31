package com.hpedrorodrigues.imagesearch.dagger.module;

import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;

import com.hpedrorodrigues.imagesearch.dagger.application.ISApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ISModule extends BaseModule {

    private final ISApplication application;

    public ISModule(ISApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    public LayoutInflater provideInflater(Context context) {
        return LayoutInflater.from(context);
    }

    @Provides
    @Singleton
    public ClipboardManager provideClipboardManager(Context context) {
        return (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }
}