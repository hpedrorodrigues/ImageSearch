package com.hpedrorodrigues.imagesearch.data.event_tracker;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;

import javax.inject.Inject;

public class AnalyticsTracker {

    @Inject
    public Tracker tracker;

    @Inject
    public AnalyticsTracker() {
    }

    public void track(final String message) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Other")
                .setAction("Event")
                .setLabel(message)
                .build());
    }

    public void track(final String message, final Object value) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Other")
                .setAction("Event")
                .setLabel(String.format("Message: %s - Value: %s", message, String.valueOf(value)))
                .build());
    }

    public void trackScreen(final String screenName) {
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void trackShare(final String message) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("UX")
                .setAction("Share")
                .setLabel("Android Provider: " + message)
                .build());
    }

    public void trackSearch(final Api api, final String query, final int page) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("UX")
                .setAction("Search")
                .setLabel((api == null ? "ALL" : api.name()) + ":" + page + "->" + query)
                .build());
    }
}