package com.hpedrorodrigues.imagesearch.ui.api.activity.view;

import com.hpedrorodrigues.imagesearch.ui.activity.AboutActivity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AboutView extends BaseView<AboutActivity> {

    public AboutView(AboutActivity activity) {
        super(activity);
    }

    @Override
    protected void onView() {
    }
}