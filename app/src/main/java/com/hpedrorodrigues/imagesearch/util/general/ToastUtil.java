package com.hpedrorodrigues.imagesearch.util.general;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

public class ToastUtil {

    @Inject
    public Context context;

    private Toast toast;

    @Inject
    public ToastUtil() {
    }

    public void showShort(int resId) {
        showShort(context.getString(resId));
    }

    public void showShort(String message) {
        show(message, Toast.LENGTH_SHORT);
    }

    public void showLong(int resId) {
        showLong(context.getString(resId));
    }

    public void showLong(String message) {
        show(message, Toast.LENGTH_LONG);
    }

    private void show(String message, int length) {
        cancel();
        toast = Toast.makeText(context, message, length);
        toast.show();
    }

    private void cancel() {
        if (toast != null) {
            if (toast.getView().isShown()) {
                toast.cancel();
            }
            toast = null;
        }
    }
}