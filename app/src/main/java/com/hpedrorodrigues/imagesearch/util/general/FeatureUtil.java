package com.hpedrorodrigues.imagesearch.util.general;

import android.support.annotation.NonNull;

import com.hpedrorodrigues.imagesearch.api.entity.Feature;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class FeatureUtil {

    @Inject
    public PermissionUtil permissionUtil;

    private Map<Integer, FeaturePermitted> features;


    @Inject
    public FeatureUtil() {
        features = new HashMap<>();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (features.containsKey(requestCode)) {

            FeaturePermitted permitted = features.remove(requestCode);

            if (permissionUtil.permissionGranted(grantResults)) {

                permitted.getListener().onPermitted(permitted.getFeature(), true);
            } else {

                permitted.getListener().onPermitted(permitted.getFeature(), false);
            }
        }
    }

    public void requestFeature(Feature feature, onFeaturePermittedListener listener) {
        if (permissionUtil.permissionGranted(feature.getPermissions())) {

            listener.onPermitted(feature, true);
        } else {

            features.put(feature.getRequestCode(), new FeaturePermitted(feature, listener));

            permissionUtil.requestPermission(
                    feature.getActivity(), feature.getRequestCode(), feature.getPermissions());
        }
    }

    private class FeaturePermitted implements Serializable {

        private final Feature feature;
        private final onFeaturePermittedListener listener;

        private FeaturePermitted(Feature feature, onFeaturePermittedListener listener) {
            this.feature = feature;
            this.listener = listener;
        }

        public Feature getFeature() {
            return feature;
        }

        public onFeaturePermittedListener getListener() {
            return listener;
        }
    }

    public interface onFeaturePermittedListener {

        void onPermitted(Feature feature, boolean permissionGranted);
    }
}