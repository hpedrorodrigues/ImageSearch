package com.hpedrorodrigues.imagesearch.api.validator;

import android.content.Context;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.entity.Image;

import javax.inject.Inject;

public class ImageValidator extends BaseValidator<Image> {

    @Inject
    public Context context;

    @Inject
    public ImageValidator() {
    }

    @Override
    public void validate(Image image) {
        int minImageSize = context.getResources().getDimensionPixelSize(R.dimen.min_image_size);
        int maxImageSize = context.getResources().getDimensionPixelSize(R.dimen.max_image_size);

        if (image.getWidth() == null) {

            image.setWidth(minImageSize);
        } else if (image.getWidth() < minImageSize) {

            image.setWidth(minImageSize);
        } else if (image.getWidth() > maxImageSize) {

            image.setWidth(maxImageSize);
        }

        if (image.getHeight() == null) {

            image.setHeight(minImageSize);
        } else if (image.getHeight() < minImageSize) {

            image.setHeight(minImageSize);
        } else if (image.getHeight() > maxImageSize) {

            image.setHeight(maxImageSize);
        }
    }
}