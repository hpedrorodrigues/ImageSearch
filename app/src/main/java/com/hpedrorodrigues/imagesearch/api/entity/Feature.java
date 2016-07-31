package com.hpedrorodrigues.imagesearch.api.entity;

import android.app.Activity;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
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
