package com.hpedrorodrigues.imagesearch.ui.api.activity.view;

import com.hpedrorodrigues.imagesearch.ui.activity.SettingsActivity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SettingsView extends BaseView<SettingsActivity> {

    public SettingsView(SettingsActivity activity) {
        super(activity);
    }

    @Override
    protected void onView() {
    }
}