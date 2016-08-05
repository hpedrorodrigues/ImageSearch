package com.hpedrorodrigues.researcher.ui.api.activity.presenter;

import android.os.Bundle;
import android.view.MenuItem;

import com.hpedrorodrigues.researcher.R;
import com.hpedrorodrigues.researcher.constant.ISConstant;
import com.hpedrorodrigues.researcher.constant.PreferenceKey;
import com.hpedrorodrigues.researcher.ui.activity.AboutActivity;
import com.hpedrorodrigues.researcher.ui.activity.SettingsActivity;
import com.hpedrorodrigues.researcher.ui.api.activity.view.SettingsView;
import com.hpedrorodrigues.researcher.ui.api.navigation.Navigator;
import com.hpedrorodrigues.researcher.util.general.MailUtil;

import javax.inject.Inject;

import de.psdev.licensesdialog.LicensesDialogFragment;

public class SettingsPresenter extends BasePresenter<SettingsActivity> {

    private final SettingsView view;

    @Inject
    public MailUtil mailUtil;

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
        view.getCloseAppContainer().setOnClickListener((v) -> {
            boolean isChecked = !view.getToggleCloseTheApp().isChecked();
            view.getToggleCloseTheApp().setChecked(isChecked);

            preferences.putBoolean(PreferenceKey.ASK_TO_EXIT, isChecked);

            answer.log("Close app check changed by container", isChecked);
        });

        view.getToggleCloseTheApp().setOnCheckedChangeListener((compoundButton, isChecked) -> {
            preferences.putBoolean(PreferenceKey.ASK_TO_EXIT, isChecked);

            answer.log("Close app check changed by switch", isChecked);
        });

        view.getKeepScreenOnContainer().setOnClickListener((v) -> {
            boolean isChecked = !view.getToggleKeepScreenOn().isChecked();
            view.getToggleKeepScreenOn().setChecked(isChecked);

            preferences.putBoolean(PreferenceKey.KEEP_SCREEN_ON, isChecked);

            answer.log("Keep screen on check changed by container", isChecked);
        });

        view.getToggleKeepScreenOn().setOnCheckedChangeListener((compoundButton, isChecked) -> {
            preferences.putBoolean(PreferenceKey.KEEP_SCREEN_ON, isChecked);

            answer.log("Keep screen on check changed by switch", isChecked);
        });

        view.getShowImagesDescription().setOnClickListener((v) -> {
            boolean isChecked = !view.getToggleShowImagesDescription().isChecked();
            view.getToggleShowImagesDescription().setChecked(isChecked);

            preferences.putBoolean(PreferenceKey.SHOW_IMAGES_DESCRIPTION, isChecked);

            answer.log("Show images description check changed by container", isChecked);
        });

        view.getToggleShowImagesDescription().setOnCheckedChangeListener((compoundButton, isChecked) -> {
            preferences.putBoolean(PreferenceKey.SHOW_IMAGES_DESCRIPTION, isChecked);

            answer.log("Show images description check changed by switch", isChecked);
        });

        view.getSafeSearch().setOnClickListener((v) -> {
            boolean isChecked = !view.getToggleSafeSearch().isChecked();
            view.getToggleSafeSearch().setChecked(isChecked);

            preferences.putBoolean(PreferenceKey.SAFE_SEARCH, isChecked);

            answer.log("Safe search check changed by container", isChecked);
        });

        view.getToggleSafeSearch().setOnCheckedChangeListener((compoundButton, isChecked) -> {
            preferences.putBoolean(PreferenceKey.SAFE_SEARCH, isChecked);

            answer.log("Safe search check changed by switch", isChecked);
        });

        view.getAboutTheApp().setOnClickListener((v) -> navigator.toActivityScreen(AboutActivity.class));

        view.getReportABug().setOnClickListener((v) -> {
            mailUtil.sendReportBugEmail(activity);

            answer.log("Report a bug action triggered");
        });

        view.getIdeaToImprove().setOnClickListener((v) -> {
            mailUtil.sendImproveAppEmail(activity);

            answer.log("Idea to improve action triggered");
        });

        view.getSendUsYourFeedback().setOnClickListener((v) -> {
            mailUtil.sendFeedbackEmail(activity);

            answer.log("Send us your feedback action triggered");
        });

        view.getContactUs().setOnClickListener((v) -> {
            mailUtil.sendContactUsEmail(activity);

            answer.log("Contact us action triggered");
        });

        view.getOpenSourceLicenses().setOnClickListener((v) -> {
            LicensesDialogFragment dialog = new LicensesDialogFragment
                    .Builder(activity)
                    .setNotices(R.raw.notices)
                    .build();

            dialog.show(activity.getSupportFragmentManager(), null);

            answer.log("Open source licenses action triggered");
        });
    }
}