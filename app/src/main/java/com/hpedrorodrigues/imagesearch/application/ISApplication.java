package com.hpedrorodrigues.imagesearch.application;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.hpedrorodrigues.imagesearch.BuildConfig;
import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.dagger.component.DaggerISComponent;
import com.hpedrorodrigues.imagesearch.dagger.component.ISComponent;
import com.hpedrorodrigues.imagesearch.dagger.module.ISModule;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ISApplication extends Application {

    private ISComponent component;
    private Tracker tracker;

    @Override
    public void onCreate() {
        super.onCreate();

        boolean isDebug = BuildConfig.DEBUG;

        // Dagger
        component = DaggerISComponent.builder().iSModule(new ISModule()).build();

        // Analytics
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        analytics.setAppOptOut(isDebug);

        tracker = analytics.newTracker(R.xml.global_tracker);
        tracker.enableAdvertisingIdCollection(true);
    }
}