package com.hpedrorodrigues.imagesearch.ui.common.component;

import android.support.v7.widget.SearchView;

import com.hpedrorodrigues.imagesearch.data.transformer.Rx;
import com.hpedrorodrigues.imagesearch.util.StringUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class SearchViewObservable {

    private final SearchView searchView;

    public SearchViewObservable(final SearchView searchView) {
        this.searchView = searchView;
    }

    public Observable<String> create() {
        return Observable
                .create(new Observable.OnSubscribe<String>() {

                    @Override
                    public void call(final Subscriber<? super String> subscriber) {
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                            @Override
                            public boolean onQueryTextSubmit(final String query) {
                                subscriber.onNext(query);
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(final String newText) {
                                subscriber.onNext(newText);
                                return true;
                            }
                        });
                    }
                })
                .filter(new Func1<String, Boolean>() {

                    @Override
                    public Boolean call(final String search) {
                        return !StringUtil.isEmpty(search);
                    }
                })
                .debounce(400, TimeUnit.MILLISECONDS)
                .compose(Rx.<String>applySchedulers());
    }
}