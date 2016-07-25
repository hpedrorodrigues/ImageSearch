package com.hpedrorodrigues.imagesearch.constant;

public enum AnimationInfo {

    FADE(ISAnimation.FADE, ISAnimation.FADE),
    ZOOM(ISAnimation.ZOOM, ISAnimation.ZOOM),
    SLIDE_LEFT(ISAnimation.SLIDE_LEFT, ISAnimation.SLIDE_RIGHT),
    SLIDE_RIGHT(ISAnimation.SLIDE_RIGHT, ISAnimation.SLIDE_LEFT),
    SLIDE_UP(ISAnimation.SLIDE_UP, ISAnimation.SLIDE_DOWN),
    SLIDE_DOWN(ISAnimation.SLIDE_DOWN, ISAnimation.SLIDE_UP);

    private final ISAnimation animation;
    private final ISAnimation reverseAnimation;

    AnimationInfo(ISAnimation animation, ISAnimation reverseAnimation) {
        this.animation = animation;
        this.reverseAnimation = reverseAnimation;
    }

    public ISAnimation getAnimation() {
        return animation;
    }

    public ISAnimation getReverseAnimation() {
        return reverseAnimation;
    }

    public static ISAnimation findReverseByAnimation(ISAnimation animation) {
        for (AnimationInfo animationInfo : AnimationInfo.values()) {

            if (animationInfo.getAnimation().equals(animation)) {

                return animationInfo.getReverseAnimation();
            }
        }

        return ISAnimation.FADE;
    }
}