package com.hpedrorodrigues.imagesearch.dagger.application;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.hpedrorodrigues.imagesearch.BuildConfig;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.dagger.component.DaggerISComponent;
import com.hpedrorodrigues.imagesearch.dagger.component.ISComponent;
import com.hpedrorodrigues.imagesearch.dagger.module.ISModule;

import io.fabric.sdk.android.Fabric;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timber.log.Timber;

@EqualsAndHashCode(callSuper = true)
@Data
public class ISApplication extends Application {

    private ISComponent component;
    private Tracker tracker;

    @Override
    public void onCreate() {
        super.onCreate();

        boolean isDebug = BuildConfig.DEBUG;

        // Timber
        Timber.plant(new Timber.DebugTree());

        // Dagger
        component = DaggerISComponent.builder().iSModule(new ISModule()).build();

        // Crashlytics and Answers
        CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(isDebug).build();
        Fabric.with(this, new Crashlytics.Builder().core(core).build());

        // Analytics
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        analytics.setAppOptOut(isDebug);

        tracker = analytics.newTracker(R.xml.global_tracker);
        tracker.enableAdvertisingIdCollection(true);
    }
}