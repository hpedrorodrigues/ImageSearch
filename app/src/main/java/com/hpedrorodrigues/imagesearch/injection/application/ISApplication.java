package com.hpedrorodrigues.imagesearch.injection.application;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.hpedrorodrigues.imagesearch.BuildConfig;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.injection.component.DaggerISComponent;
import com.hpedrorodrigues.imagesearch.injection.component.ISComponent;
import com.hpedrorodrigues.imagesearch.injection.module.ISModule;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class ISApplication extends Application {

    private ISComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        final boolean isDebug = BuildConfig.DEBUG;

        // Timber
        Timber.plant(new Timber.DebugTree());

        // Analytics
        final GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        analytics.setAppOptOut(isDebug);

        final Tracker tracker = analytics.newTracker(R.xml.global_tracker);
        tracker.enableAdvertisingIdCollection(true);

        // Dagger
        component = DaggerISComponent.builder().iSModule(new ISModule(this, tracker)).build();

        // Crashlytics and Answers
        final CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(isDebug).build();
        Fabric.with(this, new Crashlytics.Builder().core(core).build());
    }

    public ISComponent getComponent() {
        return component;
    }
}