package com.hpedrorodrigues.imagesearch.ui.adapter;

import android.widget.BaseAdapter;

import com.hpedrorodrigues.imagesearch.util.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
abstract class ISBaseAdapter<T> extends BaseAdapter {

    private List<T> content;

    public T getItemTyped(int i) {
        return (T) getItem(i);
    }

    public void add(List<T> content) {
        if (isEmpty()) {
            setContent(new ArrayList<>());
        }

        this.content.addAll(content);
    }

    public void clear() {
        if (!isEmpty()) {
            content.clear();
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