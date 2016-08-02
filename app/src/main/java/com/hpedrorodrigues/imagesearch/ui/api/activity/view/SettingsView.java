package com.hpedrorodrigues.imagesearch.ui.api.activity.view;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.ui.activity.SettingsActivity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SettingsView extends BaseView<SettingsActivity> {

    private RelativeLayout closeAppContainer;
    private Switch toggleCloseTheApp;

    private RelativeLayout keepScreenOnContainer;
    private Switch toggleKeepScreenOn;

    private RelativeLayout showImagesDescription;
    private Switch toggleShowImagesDescription;

    private RelativeLayout safeSearch;
    private Switch toggleSafeSearch;

    private LinearLayout aboutTheApp;
    private LinearLayout rateTheApp;
    private LinearLayout shareTheApp;
    private LinearLayout reportABug;
    private LinearLayout ideaToImprove;
    private LinearLayout sendUsYourFeedback;
    private LinearLayout contactUs;
    private LinearLayout openSourceLicenses;

    public SettingsView(SettingsActivity activity) {
        super(activity);
    }

    @Override
    public void onView() {
        closeAppContainer = (RelativeLayout) activity.findViewById(R.id.closeApp);
        toggleCloseTheApp = (Switch) activity.findViewById(R.id.toggleCloseTheApp);

        keepScreenOnContainer = (RelativeLayout) activity.findViewById(R.id.keepScreenOn);
        toggleKeepScreenOn = (Switch) activity.findViewById(R.id.toggleKeepScreenOn);

        showImagesDescription = (RelativeLayout) activity.findViewById(R.id.showImagesDescription);
        toggleShowImagesDescription = (Switch) activity.findViewById(R.id.toggleShowImagesDescription);

        safeSearch = (RelativeLayout) activity.findViewById(R.id.safeSearch);
        toggleSafeSearch = (Switch) activity.findViewById(R.id.toggleSafeSearch);

        aboutTheApp = (LinearLayout) activity.findViewById(R.id.aboutTheApp);
        rateTheApp = (LinearLayout) activity.findViewById(R.id.rateTheApp);
        shareTheApp = (LinearLayout) activity.findViewById(R.id.shareTheApp);
        reportABug = (LinearLayout) activity.findViewById(R.id.reportABug);
        ideaToImprove = (LinearLayout) activity.findViewById(R.id.ideaToImprove);
        sendUsYourFeedback = (LinearLayout) activity.findViewById(R.id.sendUsYourFeedback);
        contactUs = (LinearLayout) activity.findViewById(R.id.contactUs);
        openSourceLicenses = (LinearLayout) activity.findViewById(R.id.openSourceLicenses);
    }
}