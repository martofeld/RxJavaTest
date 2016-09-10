package com.mfeldsztejn.rxjavatest.main.listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by mfeldsztejn on 9/10/16.
 */

public class PagingOnScrollListener extends RecyclerView.OnScrollListener {

    private static int PAGE_SIZE = 10;
    private Callback callback;

    public PagingOnScrollListener(Callback callback) {
        this.callback = callback;
    }

    public PagingOnScrollListener(Callback callback, int pageSize) {
        this.callback = callback;
        PAGE_SIZE = pageSize;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        if (didScrollEnough(layoutManager)) {
            callback.onScrolledEnough();
        }
    }

    private boolean didScrollEnough(LinearLayoutManager layoutManager) {
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        return (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE;
    }

    public interface Callback {
        void onScrolledEnough();
    }
}
