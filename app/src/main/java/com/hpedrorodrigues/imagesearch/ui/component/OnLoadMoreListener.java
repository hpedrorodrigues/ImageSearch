package com.hpedrorodrigues.imagesearch.ui.component;

import android.widget.AbsListView;

public class OnLoadMoreListener implements AbsListView.OnScrollListener {

    private final int leftItemsToLoadMore;

    private final OnMoreListener onMoreListener;

    private boolean canLoadMore;

    public OnLoadMoreListener(int leftItemsToLoadMore, OnLoadMoreListener.OnMoreListener onMoreListener) {
        this.leftItemsToLoadMore = leftItemsToLoadMore;
        this.onMoreListener = onMoreListener;
        this.canLoadMore = true;
    }

    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
    }

    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem,
                         final int visibleItemCount, final int totalItemCount) {

        if (canLoadMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (totalItemCount > 0 && lastInScreen + leftItemsToLoadMore >= totalItemCount) {

                onMoreListener.onMore();
                canLoadMore = false;
            }
        }
    }

    public void enableLoadMore() {
        this.canLoadMore = true;
    }

    public interface OnMoreListener {

        void onMore();
    }
}