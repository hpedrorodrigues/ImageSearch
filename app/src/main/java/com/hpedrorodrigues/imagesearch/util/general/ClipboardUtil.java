package com.hpedrorodrigues.imagesearch.util.general;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import com.hpedrorodrigues.imagesearch.R;

import javax.inject.Inject;

public class ClipboardUtil {

    @Inject
    public ClipboardManager clipboardManager;

    @Inject
    public Context context;

    @Inject
    public ClipboardUtil() {
    }

    public void copy(String text) {
        ClipData clip = ClipData.newPlainText(text, text);
        clipboardManager.setPrimaryClip(clip);

        Toast.makeText(
                context,
                context.getString(R.string.text_copied_to_clipboard, text),
                Toast.LENGTH_LONG
        ).show();
    }
}