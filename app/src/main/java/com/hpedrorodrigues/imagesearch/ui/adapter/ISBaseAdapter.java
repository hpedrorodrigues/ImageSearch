package com.hpedrorodrigues.imagesearch.ui.adapter;

import android.database.DataSetObserver;
import android.widget.ListAdapter;

import com.hpedrorodrigues.imagesearch.util.CollectionUtil;

import java.util.List;

import lombok.Data;

@Data
abstract class ISBaseAdapter<T> implements ListAdapter {

    private List<T> content;

    public T getItemTyped(int i) {
        return (T) getItem(i);
    }

    public void clear() {
        if (!isEmpty()) {
            content.clear();
        }
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return isEmpty() ? null : content.get(i);
    }

    @Override
    public int getCount() {
        return isEmpty() ? 0 : content.size();
    }

    @Override
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(content);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int i) {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }
}