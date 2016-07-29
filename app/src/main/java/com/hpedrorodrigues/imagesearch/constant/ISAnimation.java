package com.hpedrorodrigues.imagesearch.constant;

public enum ISAnimation {
    FADE(0),
    ZOOM(1),
    SLIDE_LEFT(2),
    SLIDE_RIGHT(3),
    SLIDE_UP(4),
    SLIDE_DOWN(5);

    private int order;

    ISAnimation(int order) {
        this.order = order;
    }

    public static ISAnimation find(int order) {
        for (ISAnimation animation : ISAnimation.values()) {

            if (order == animation.order) {

                return animation;
            }
        }

        return FADE;
    }

    public int getOrder() {
        return order;
    }
}