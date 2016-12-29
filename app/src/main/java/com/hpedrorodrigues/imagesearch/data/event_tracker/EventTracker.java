package com.hpedrorodrigues.imagesearch.data.event_tracker;

import com.hpedrorodrigues.imagesearch.api.network.api.Api;

import javax.inject.Inject;

public class EventTracker {

    @Inject
    public FabricAnswer answer;

    @Inject
    public AnalyticsTracker tracker;

    @Inject
    public EventTracker() {
    }

    public void logActivityScreen(final String screenName) {
        answer.logActivityScreen(screenName);
        tracker.trackScreen(screenName);
    }

    public void logFragmentScreen(final String screenName) {
        answer.logFragmentScreen(screenName);
        tracker.trackScreen(screenName);
    }

    public void track(final String message) {
        answer.log(message);
        tracker.track(message);
    }

    public void track(final String message, final Object value) {
        answer.log(message, value);
        tracker.track(message, value);
    }

    public void trackShare(final String message) {
        answer.logShare(message);
        tracker.trackShare(message);
    }

    public void trackSearch(final Api api, final String query, final int page) {
        answer.logSearch(api, query, page);
        tracker.trackSearch(api, query, page);
    }
}