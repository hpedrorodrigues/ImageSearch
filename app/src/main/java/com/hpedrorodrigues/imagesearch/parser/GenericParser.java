package com.hpedrorodrigues.imagesearch.parser;

import com.hpedrorodrigues.imagesearch.entity.Image;
import com.hpedrorodrigues.imagesearch.network.api.Api;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

@SuppressWarnings("unchecked")
public class GenericParser {

    @Inject
    public FlickrParser flickrParser;

    @Inject
    public CSEParser cseParser;

    @Inject
    public ImgurParser imgurParser;

    @Inject
    public DuckDuckGoParser duckDuckGoParser;

    @Inject
    public BingParser bingParser;

    @Inject
    public GenericParser() {
    }

    public List<Image> parse(Api api, Map data) {
        switch (api) {

            case FLICKR:
                return flickrParser.parse(data);

            case CSE:
                return cseParser.parse(data);

            case IMGUR:
                return imgurParser.parse(data);

            case DUCK_DUCK_GO:
                return duckDuckGoParser.parse(data);

            case BING:
                return bingParser.parse(data);

            default:
                throw new IllegalArgumentException("Unsupported api " + api);
        }
    }
}