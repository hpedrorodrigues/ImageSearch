package com.hpedrorodrigues.imagesearch.data.event_tracker;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.answers.SearchEvent;
import com.crashlytics.android.answers.ShareEvent;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;

import javax.inject.Inject;

public class FabricAnswer {

    @Inject
    public FabricAnswer() {
    }

    public Answers get() {
        return Answers.getInstance();
    }

    public void log(final String message) {
        get().logCustom(new CustomEvent(message));
    }

    public void log(final String message, final Object value) {
        get().logCustom(new CustomEvent(message).putCustomAttribute("Value", String.valueOf(value)));
    }

    public void logActivityScreen(final String screenName) {
        get().logContentView(new ContentViewEvent()
                .putContentId("Screen")
                .putContentName(screenName)
                .putContentType("Activity"));
    }

    public void logFragmentScreen(final String screenName) {
        get().logContentView(new ContentViewEvent()
                .putContentId("Screen")
                .putContentName(screenName)
                .putContentType("Fragment"));
    }

    public void logShare(final String message) {
        get().logShare(new ShareEvent().putMethod("Android Provider").putContentName(message));
    }

    public void logSearch(final Api api, final String query, final int page) {
        get().logSearch(new SearchEvent()
                .putQuery(query)
                .putCustomAttribute("Api", api == null ? "ALL" : api.name())
                .putCustomAttribute("Page", String.valueOf(page))
        );
    }
}