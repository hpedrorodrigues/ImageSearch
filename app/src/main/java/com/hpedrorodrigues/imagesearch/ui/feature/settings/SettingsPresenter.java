package com.hpedrorodrigues.imagesearch.ui.feature.settings;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.constant.PreferenceKey;
import com.hpedrorodrigues.imagesearch.ui.base.BasePresenter;
import com.hpedrorodrigues.imagesearch.ui.common.navigation.Navigator;
import com.hpedrorodrigues.imagesearch.util.external.EmailSender;
import com.hpedrorodrigues.imagesearch.util.external.ExternalApp;
import com.hpedrorodrigues.imagesearch.util.general.ShareUtil;

import javax.inject.Inject;

import de.psdev.licensesdialog.LicensesDialogFragment;

public class SettingsPresenter extends BasePresenter<SettingsActivity> {

    private final SettingsView view;

    @Inject
    public EmailSender emailSender;

    @Inject
    public ShareUtil shareUtil;

    @Inject
    public ExternalApp externalApp;

    public SettingsPresenter(SettingsActivity activity, Navigator navigator) {
        super(activity, navigator);
        view = new SettingsView(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        view.onView();

        loadValues();
        setUpListeners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigator.toActivityParent();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadValues() {
        boolean closeApp = preferences.getBoolean(PreferenceKey.ASK_TO_EXIT,
                ISConstant.DEFAULT_ASK_TO_EXIT);
        view.getToggleCloseTheApp().setChecked(closeApp);

        boolean keepScreenOn = preferences.getBoolean(PreferenceKey.KEEP_SCREEN_ON,
                ISConstant.DEFAULT_KEEP_SCREEN_ON);
        view.getToggleKeepScreenOn().setChecked(keepScreenOn);

        boolean showImagesDescription = preferences
                .getBoolean(PreferenceKey.SHOW_IMAGES_DESCRIPTION, ISConstant.DEFAULT_SHOW_IMAGES_DESCRIPTION);
        view.getToggleShowImagesDescription().setChecked(showImagesDescription);

        boolean safeSearch = preferences
                .getBoolean(PreferenceKey.SAFE_SEARCH, ISConstant.DEFAULT_SAFE_SEARCH);
        view.getToggleSafeSearch().setChecked(safeSearch);
    }

    private void setUpListeners() {
        view.getCloseAppContainer().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isChecked = !view.getToggleCloseTheApp().isChecked();

                view.getToggleCloseTheApp().setChecked(isChecked);
                preferences.putBoolean(PreferenceKey.ASK_TO_EXIT, isChecked);
                eventTracker.track("Close app check changed by container", isChecked);
            }
        });

        view.getToggleCloseTheApp()
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        preferences.putBoolean(PreferenceKey.ASK_TO_EXIT, isChecked);
                        eventTracker.track("Close app check changed by switch", isChecked);
                    }
                });

        view.getKeepScreenOnContainer().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isChecked = !view.getToggleKeepScreenOn().isChecked();

                view.getToggleKeepScreenOn().setChecked(isChecked);
                preferences.putBoolean(PreferenceKey.KEEP_SCREEN_ON, isChecked);
                eventTracker.track("Keep screen on check changed by container", isChecked);
            }
        });

        view.getToggleKeepScreenOn()
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        preferences.putBoolean(PreferenceKey.KEEP_SCREEN_ON, isChecked);
                        eventTracker.track("Keep screen on check changed by switch", isChecked);
                    }
                });

        view.getShowImagesDescription().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isChecked = !view.getToggleShowImagesDescription().isChecked();

                view.getToggleShowImagesDescription().setChecked(isChecked);
                preferences.putBoolean(PreferenceKey.SHOW_IMAGES_DESCRIPTION, isChecked);
                eventTracker.track("Show images description check changed by container", isChecked);
            }
        });

        view.getToggleShowImagesDescription()
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        preferences.putBoolean(PreferenceKey.SHOW_IMAGES_DESCRIPTION, isChecked);
                        eventTracker.track("Show images description check changed by switch", isChecked);
                    }
                });

        view.getSafeSearch().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isChecked = !view.getToggleSafeSearch().isChecked();

                view.getToggleSafeSearch().setChecked(isChecked);
                preferences.putBoolean(PreferenceKey.SAFE_SEARCH, isChecked);
                eventTracker.track("Safe search check changed by container", isChecked);
            }
        });

        view.getToggleSafeSearch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferences.putBoolean(PreferenceKey.SAFE_SEARCH, isChecked);
                eventTracker.track("Safe search check changed by switch", isChecked);
            }
        });

        view.getReportABug().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                emailSender.sendReportBugEmail(activity);
                eventTracker.track("Report a bug action triggered");
            }
        });

        view.getRateTheApp().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                externalApp.openPlayStore(activity);
                eventTracker.track("Rate the app action triggered");
            }
        });

        view.getShareTheApp().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                shareUtil.shareApp(activity);
                eventTracker.trackShare("Share the app action triggered");
            }
        });

        view.getIdeaToImprove().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                emailSender.sendImproveAppEmail(activity);
                eventTracker.track("Idea to improve action triggered");
            }
        });

        view.getSendUsYourFeedback().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                emailSender.sendFeedbackEmail(activity);
                eventTracker.track("Send us your feedback action triggered");
            }
        });

        view.getContactUs().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                emailSender.sendContactUsEmail(activity);
                eventTracker.track("Contact us action triggered");
            }
        });

        view.getOpenSourceLicenses().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LicensesDialogFragment dialog = new LicensesDialogFragment
                        .Builder(activity)
                        .setNotices(R.raw.notices)
                        .build();

                dialog.show(activity.getSupportFragmentManager(), null);
                eventTracker.track("Open source licenses action triggered");
            }
        });
    }
}