package com.hpedrorodrigues.imagesearch.api.entity;

import android.app.Activity;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Feature implements Serializable {

    private Activity activity;

    @Setter(AccessLevel.NONE)
    private String[] permissions;

    private int requestCode;

    private Serializable value;

    public void setPermissions(String... permissions) {
        this.permissions = permissions;
    }
}
