package com.hpedrorodrigues.imagesearch.parser;

import com.hpedrorodrigues.imagesearch.dagger.component.ISComponent;
import com.hpedrorodrigues.imagesearch.entity.Image;
import com.hpedrorodrigues.imagesearch.network.api.Api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class GenericParser {

    private static final List<BaseParser> PARSERS = Arrays.asList(
            new FlickrParser(),
            new CSEParser(),
            new ImgurParser(),
            new DuckDuckGoParser(),
            new BingParser()
    );

    @Inject
    public GenericParser(ISComponent component) {
        for (BaseParser baseParser : PARSERS) {
            component.inject(baseParser);
        }
    }

    public List<Image> parse(Api api, Map data) {
        for (BaseParser parser : PARSERS) {

            if (parser.getApi().equals(api)) {

                return parser.parse(data);
            }
        }

        return null;
    }
}