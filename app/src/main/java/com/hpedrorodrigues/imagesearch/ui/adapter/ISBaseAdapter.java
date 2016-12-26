package com.hpedrorodrigues.imagesearch.ui.adapter;

import android.widget.BaseAdapter;

import com.hpedrorodrigues.imagesearch.util.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

abstract class ISBaseAdapter<T> extends BaseAdapter {

    private List<T> content;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
        notifyDataSetChanged();
    }

    public T getItemTyped(int i) {
        return (T) getItem(i);
    }

    public void add(List<T> content) {
        if (isEmpty()) {
            setContent(new ArrayList<>());
        }

        this.content.addAll(content);
        notifyDataSetChanged();
    }

    public void clear() {
        if (!isEmpty()) {
            content.clear();
            notifyDataSetChanged();
        }
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
}