package com.hpedrorodrigues.imagesearch.constant;

import com.hpedrorodrigues.imagesearch.R;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;

public enum DrawerItem {

    FLICKR(Api.FLICKR, R.id.flickr_item),
    CSE(Api.CSE, R.id.cse_item),
    IMGUR(Api.IMGUR, R.id.imgur_item),
    DUCK_DUCK_GO(Api.DUCK_DUCK_GO, R.id.duckduckgo_item),
    BING(Api.BING, R.id.bing_item),
    PIXABAY(Api.PIXABAY, R.id.pixabay_item),
    GIPHY(Api.GIPHY, R.id.giphy_item);

    private final Api api;
    private final int id;

    DrawerItem(Api api, int id) {
        this.api = api;
        this.id = id;
    }

    public static DrawerItem find(int itemId) {
        for (DrawerItem item : values()) {

            if (item.getId() == itemId) {

                return item;
            }
        }

        return null;
    }

    public Api getApi() {
        return api;
    }

    public int getId() {
        return id;
    }
}