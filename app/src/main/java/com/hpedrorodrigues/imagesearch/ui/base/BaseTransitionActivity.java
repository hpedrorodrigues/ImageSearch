package com.hpedrorodrigues.imagesearch.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.data.constant.AnimationInfo;
import com.hpedrorodrigues.imagesearch.data.constant.BundleKey;
import com.hpedrorodrigues.imagesearch.data.constant.ISAnimation;

import static com.hpedrorodrigues.imagesearch.data.constant.ISAnimation.FADE;
import static com.hpedrorodrigues.imagesearch.data.constant.ISAnimation.SLIDE_DOWN;
import static com.hpedrorodrigues.imagesearch.data.constant.ISAnimation.SLIDE_LEFT;
import static com.hpedrorodrigues.imagesearch.data.constant.ISAnimation.SLIDE_RIGHT;
import static com.hpedrorodrigues.imagesearch.data.constant.ISAnimation.SLIDE_UP;
import static com.hpedrorodrigues.imagesearch.data.constant.ISAnimation.ZOOM;
import static com.hpedrorodrigues.imagesearch.data.constant.ISAnimation.find;

abstract class BaseTransitionActivity extends AppCompatActivity {

    protected ISAnimation currentAnimation = FADE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadAnimation();
    }

    @Override
    public void finish() {
        super.finish();

        overrideTransitionWithReverse();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overrideTransitionWithReverse();
    }

    private void loadAnimation() {
        if (getIntent() != null && getIntent().hasExtra(BundleKey.ARG_ANIMATION)) {

            int animationOrder = getIntent()
                    .getIntExtra(BundleKey.ARG_ANIMATION, FADE.getOrder());

            currentAnimation = find(animationOrder);
        } else {

            currentAnimation = FADE;
        }
    }

    protected <A extends BaseActivity> void startWithFade(Class<A> activityClass) {
        start(activityClass, FADE);
    }

    protected <A extends BaseActivity> void startWithZoom(Class<A> activityClass) {
        start(activityClass, ZOOM);
    }

    protected <A extends BaseActivity> void startWithSlideLeft(Class<A> activityClass) {
        start(activityClass, SLIDE_LEFT);
    }

    protected <A extends BaseActivity> void startWithSlideRight(Class<A> activityClass) {
        start(activityClass, SLIDE_RIGHT);
    }

    protected <A extends BaseActivity> void startWithSlideUp(Class<A> activityClass) {
        start(activityClass, SLIDE_UP);
    }

    protected <A extends BaseActivity> void startWithSlideDown(Class<A> activityClass) {
        start(activityClass, SLIDE_DOWN);
    }

    protected void startWithFade(Intent intent) {
        start(FADE, intent);
    }

    protected void startWithZoom(Intent intent) {
        start(ZOOM, intent);
    }

    protected void startWithSlideLeft(Intent intent) {
        start(SLIDE_LEFT, intent);
    }

    protected void startWithSlideRight(Intent intent) {
        start(SLIDE_RIGHT, intent);
    }

    protected void startWithSlideUp(Intent intent) {
        start(SLIDE_UP, intent);
    }

    protected void startWithSlideDown(Intent intent) {
        start(SLIDE_DOWN, intent);
    }

    protected <A extends BaseActivity> void startWithResultAndFade(Class<A> activityClass,
                                                                   int requestCode) {
        startWithResult(activityClass, requestCode, FADE);
    }

    protected <A extends BaseActivity> void startWithResultAndZoom(Class<A> activityClass,
                                                                   int requestCode) {
        startWithResult(activityClass, requestCode, ZOOM);
    }

    protected <A extends BaseActivity> void startWithResultAndSlideLeft(Class<A> activityClass,
                                                                        int requestCode) {
        startWithResult(activityClass, requestCode, SLIDE_LEFT);
    }

    protected <A extends BaseActivity> void startWithResultAndSlideRight(Class<A> activityClass,
                                                                         int requestCode) {
        startWithResult(activityClass, requestCode, SLIDE_RIGHT);
    }

    protected <A extends BaseActivity> void startWithResultAndSlideUp(Class<A> activityClass,
                                                                      int requestCode) {
        startWithResult(activityClass, requestCode, SLIDE_UP);
    }

    protected <A extends BaseActivity> void startWithResultAndSlideDown(Class<A> activityClass,
                                                                        int requestCode) {
        startWithResult(activityClass, requestCode, SLIDE_DOWN);
    }

    protected void replaceFragmentWithFade(int containerId, Fragment fragment) {
        replaceFragment(containerId, fragment, FADE);
    }

    protected void replaceFragmentWithZoom(int containerId, Fragment fragment) {
        replaceFragment(containerId, fragment, ZOOM);
    }

    protected void replaceFragmentWithSlideLeft(int containerId, Fragment fragment) {
        replaceFragment(containerId, fragment, SLIDE_LEFT);
    }

    protected void replaceFragmentWithSlideRight(int containerId, Fragment fragment) {
        replaceFragment(containerId, fragment, SLIDE_RIGHT);
    }

    protected void replaceFragmentWithSlideUp(int containerId, Fragment fragment) {
        replaceFragment(containerId, fragment, SLIDE_UP);
    }

    protected void replaceFragmentWithSlideDown(int containerId, Fragment fragment) {
        replaceFragment(containerId, fragment, SLIDE_DOWN);
    }

    private <A extends BaseActivity> void start(Class<A> activityClass, ISAnimation animation) {
        Intent intent = new Intent(this, activityClass);

        ISAnimation reverseAnimation = AnimationInfo.findReverseByAnimation(animation);
        intent.putExtra(BundleKey.ARG_ANIMATION, reverseAnimation.getOrder());

        startActivity(intent);
        overrideTransition(animation);
    }

    private void start(ISAnimation animation, Intent intent) {
        ISAnimation reverseAnimation = AnimationInfo.findReverseByAnimation(animation);
        intent.putExtra(BundleKey.ARG_ANIMATION, reverseAnimation.getOrder());

        startActivity(intent);
        overrideTransition(animation);
    }

    private <A extends BaseActivity> void startWithResult(Class<A> activityClass,
                                                          int requestCode, ISAnimation animation) {
        ISAnimation reverseAnimation = AnimationInfo.findReverseByAnimation(animation);
        Intent intent = new Intent(this, activityClass);
        intent.putExtra(BundleKey.ARG_ANIMATION, reverseAnimation.getOrder());

        startActivityForResult(intent, requestCode);
        overrideTransition(animation);
    }

    private void replaceFragment(int containerId, Fragment fragment, ISAnimation animation) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        ISAnimation reverseAnimation = AnimationInfo.findReverseByAnimation(animation);

        Bundle bundle = fragment.getArguments() == null ? new Bundle() : fragment.getArguments();

        bundle.putInt(BundleKey.ARG_ANIMATION, reverseAnimation.getOrder());

        fragment.setArguments(bundle);

        switch (animation) {
            case FADE:
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                break;
            case ZOOM:
                transaction.setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out);
                break;
            case SLIDE_LEFT:
                transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right);
                break;
            case SLIDE_RIGHT:
                transaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_out_right);
                break;
            case SLIDE_UP:
                transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up);
                break;
            case SLIDE_DOWN:
                transaction.setCustomAnimations(R.anim.slide_in_down, R.anim.slide_out_down);
                break;
            default:
                throw new IllegalArgumentException("Invalid animation $animation");
        }

        transaction.replace(containerId, fragment).commit();
        currentAnimation = animation;
    }

    private void overrideTransition(ISAnimation animation) {
        switch (animation) {
            case FADE:
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case ZOOM:
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
            case SLIDE_LEFT:
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case SLIDE_RIGHT:
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
            case SLIDE_UP:
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                break;
            case SLIDE_DOWN:
                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
                break;
            default:
                throw new IllegalArgumentException("Invalid animation $animation");
        }

        currentAnimation = animation;
    }

    private void overrideTransitionWithReverse() {
        overrideTransition(AnimationInfo.findReverseByAnimation(currentAnimation));
    }

    protected void overrideTransitionWithFade() {
        overrideTransition(ISAnimation.FADE);
    }

    protected void overrideTransitionWithZoom() {
        overrideTransition(ISAnimation.ZOOM);
    }

    protected void overrideTransitionWithSlideLeft() {
        overrideTransition(ISAnimation.SLIDE_LEFT);
    }

    protected void overrideTransitionWithSlideRight() {
        overrideTransition(ISAnimation.SLIDE_RIGHT);
    }

    protected void overrideTransitionWithSlideUp() {
        overrideTransition(ISAnimation.SLIDE_UP);
    }

    protected void overrideTransitionWithSlideDown() {
        overrideTransition(ISAnimation.SLIDE_DOWN);
    }
}