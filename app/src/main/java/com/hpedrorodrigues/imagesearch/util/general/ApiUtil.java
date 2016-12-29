package com.hpedrorodrigues.imagesearch.util.general;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.data.remote.api.Api;

import javax.inject.Inject;

public class ApiUtil {

    @Inject
    public ApiUtil() {
    }

    public int getProviderNameByApi(final Api api) {
        if (api == null) {

            return R.string.all_provider;
        } else if (api.equals(Api.FLICKR)) {

            return R.string.flickr_provider;
        } else if (api.equals(Api.CSE)) {

            return R.string.google_provider;
        } else if (api.equals(Api.IMGUR)) {

            return R.string.imgur_provider;
        } else if (api.equals(Api.DUCK_DUCK_GO)) {

            return R.string.duck_duck_go_provider;
        } else if (api.equals(Api.BING)) {

            return R.string.bing_provider;
        } else if (api.equals(Api.PIXABAY)) {

            return R.string.pixabay_provider;
        } else {

            return R.string.giphy_provider;
        }
    }
}