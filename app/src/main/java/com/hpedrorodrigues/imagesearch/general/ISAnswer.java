package com.hpedrorodrigues.imagesearch.general;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.answers.ShareEvent;

import javax.inject.Inject;

public class ISAnswer {

    @Inject
    public ISAnswer() {
    }

    public Answers instance() {
        return Answers.getInstance();
    }

    public void log(String message) {
        instance().logCustom(new CustomEvent(message));
    }

    public void log(String message, String value) {
        instance().logCustom(new CustomEvent(message).putCustomAttribute("Value", value));
    }

    public void log(String message, String attrName, String value) {
        instance().logCustom(new CustomEvent(message).putCustomAttribute(attrName, value));
    }

    public void logShare(String message, String value) {
        instance().logShare(
                new ShareEvent()
                        .putMethod("Android Provider")
                        .putContentName(message)
                        .putCustomAttribute("Value", value)
        );
    }

    public void logShare(String message) {
        instance().logShare(new ShareEvent().putMethod("Android Provider").putContentName(message));
    }
}