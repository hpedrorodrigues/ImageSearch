package com.hpedrorodrigues.imagesearch.network.api.flickr;

public enum FlickrMethod {

    RECENT("flickr.photos.getRecent"),
    POPULAR("flickr.interestingness.getList"),
    PHOTO_DETAIL("flickr.photos.getInfo"),
    COMMENTS("flickr.photos.comments.getList"),
    SEARCH("flickr.photos.search");

    private final String id;

    FlickrMethod(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}