package com.hpedrorodrigues.imagesearch.util.rx;

import android.support.v7.widget.SearchView;

import com.hpedrorodrigues.imagesearch.util.StringUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SearchViewObservable {

    private final SearchView searchView;

    public SearchViewObservable(SearchView searchView) {
        this.searchView = searchView;
    }

    public Observable<String> create() {
        return Observable
                .create((Subscriber<? super String> subscriber) -> {

                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            subscriber.onNext(newText);
                            return true;
                        }
                    });

                })
                .filter(search -> !StringUtil.isEmpty(search))
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }
}