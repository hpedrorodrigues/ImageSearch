package com.hpedrorodrigues.imagesearch.util.general;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.util.info.DeviceInfo;

import javax.inject.Inject;

import timber.log.Timber;

public class MailUtil {

    @Inject
    public IntentUtil intentUtil;

    @Inject
    public DeviceInfo deviceInfo;

    @Inject
    public MailUtil() {
    }

    public void sendImproveAppEmail(Activity activity) {
        send(activity, R.string.suggestions);
    }

    public void sendReportBugEmail(Activity activity) {
        send(activity, R.string.new_bug);
    }

    public void sendFeedbackEmail(Activity activity) {
        send(activity, R.string.feedback);
    }

    public void sendContactUsEmail(Activity activity) {
        send(activity, R.string.contact);
    }

    private void send(Activity activity, int resId) {
        try {
            Intent intent = buildIntent(activity, resId);
            intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent intent = buildIntent(activity, resId);
            if (intentUtil.isAvailable(activity, intent)) {
                activity.startActivity(
                        Intent.createChooser(intent, activity.getString(R.string.choose_app)));
            } else {
                Timber.e(e, "This intent is not available");
            }
        }
    }

    private Intent buildIntent(Activity activity, int resId) {
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_EMAIL, ISConstant.EMAIL);

        String subject = activity.getString(R.string.app_name) + " - " + activity.getString(resId);

        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, deviceInfo.getDetails());
        intent.setType("message/rfc822");

        return intent;
    }
}