package com.hpedrorodrigues.imagesearch.api.entity;

import android.app.Activity;

import java.io.Serializable;

public class Feature implements Serializable {

    private Activity activity;

    private String[] permissions;

    private int requestCode;

    private Serializable value;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(final Activity activity) {
        this.activity = activity;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(final String... permissions) {
        this.permissions = permissions;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(final int requestCode) {
        this.requestCode = requestCode;
    }

    public Serializable getValue() {
        return value;
    }

    public void setValue(final Serializable value) {
        this.value = value;
    }
}