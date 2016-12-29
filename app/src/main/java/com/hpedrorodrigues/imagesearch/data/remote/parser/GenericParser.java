package com.hpedrorodrigues.imagesearch.data.remote.parser;

import com.hpedrorodrigues.imagesearch.data.model.Image;
import com.hpedrorodrigues.imagesearch.data.remote.api.Api;
import com.hpedrorodrigues.imagesearch.injection.component.ISComponent;

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
            new BingParser(),
            new PixabayParser(),
            new GiphyParser()
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