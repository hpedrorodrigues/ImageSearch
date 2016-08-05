package com.hpedrorodrigues.researcher.util.general;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.hpedrorodrigues.researcher.R;

import javax.inject.Inject;

public class ClipboardUtil {

    @Inject
    public ClipboardManager clipboardManager;

    @Inject
    public Context context;

    @Inject
    public ToastUtil toastUtil;

    @Inject
    public ClipboardUtil() {
    }

    public void copy(String text) {
        ClipData clip = ClipData.newPlainText(text, text);
        clipboardManager.setPrimaryClip(clip);

        toastUtil.showLong(context.getString(R.string.text_copied_to_clipboard, text));
    }
}