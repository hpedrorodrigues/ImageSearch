package com.hpedrorodrigues.imagesearch.util.external;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.data.constant.ISConstant;
import com.hpedrorodrigues.imagesearch.util.general.DeviceInfoUtil;
import com.hpedrorodrigues.imagesearch.util.general.IntentUtil;

import javax.inject.Inject;

import timber.log.Timber;

public class EmailSender {

    @Inject
    public IntentUtil intentUtil;

    @Inject
    public DeviceInfoUtil deviceInfoUtil;

    @Inject
    public EmailSender() {
    }

    public void sendImproveAppEmail(final Activity activity) {
        send(activity, R.string.suggestions);
    }

    public void sendReportBugEmail(final Activity activity) {
        send(activity, R.string.new_bug);
    }

    public void sendFeedbackEmail(final Activity activity) {
        send(activity, R.string.feedback);
    }

    public void sendContactUsEmail(final Activity activity) {
        send(activity, R.string.contact);
    }

    private void send(final Activity activity, final int resId) {
        try {
            final Intent intent = buildIntent(activity, resId);
            intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            activity.startActivity(intent);
        } catch (final ActivityNotFoundException e) {
            final Intent intent = buildIntent(activity, resId);
            if (intentUtil.isAvailable(activity, intent)) {
                activity.startActivity(
                        Intent.createChooser(intent, activity.getString(R.string.choose_app)));
            } else {
                Timber.e(e, "This intent is not available");
            }
        }
    }

    private Intent buildIntent(final Activity activity, final int resId) {
        final Intent intent = new Intent(Intent.ACTION_SEND);

        final String subject = activity.getString(R.string.app_name) + " - " + activity.getString(resId);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ISConstant.EMAIL});
        intent.putExtra(Intent.EXTRA_TEXT, deviceInfoUtil.getDetails());
        intent.setType("message/rfc822");

        return intent;
    }
}