package com.hpedrorodrigues.imagesearch.dagger.module;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;

import com.google.android.gms.analytics.Tracker;
import com.hpedrorodrigues.imagesearch.component.receiver.observable.NetworkStateObservable;
import com.hpedrorodrigues.imagesearch.dagger.application.ISApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ISModule extends BaseModule {

    private final ISApplication application;
    private final Tracker tracker;

    public ISModule(final ISApplication application, final Tracker tracker) {
        this.application = application;
        this.tracker = tracker;
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

    @Provides
    @Singleton
    public DownloadManager provideDownloadManager(Context context) {
        return (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    @Provides
    @Singleton
    public SharedPreferences providePreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    public ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    public NetworkStateObservable provideNetworkStateObservable() {
        return new NetworkStateObservable();
    }

    @Provides
    @Singleton
    public WallpaperManager provideWallpaperManager(Context context) {
        return WallpaperManager.getInstance(context);
    }

    @Provides
    @Singleton
    public Tracker provideTracker() {
        return tracker;
    }
}