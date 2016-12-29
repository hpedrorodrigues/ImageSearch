package com.hpedrorodrigues.imagesearch.data.transformer;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Rx {

    private static final Observable.Transformer transformer = new Observable.Transformer<Object, Object>() {

        @Override
        @SuppressWarnings("unchecked")
        public Observable call(final Observable observable) {
            return observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    @SuppressWarnings("unchecked")
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) transformer;
    }
}