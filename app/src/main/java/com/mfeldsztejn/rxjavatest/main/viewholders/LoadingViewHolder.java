package com.mfeldsztejn.rxjavatest.main.viewholders;

import android.view.View;

import com.mfeldsztejn.rxjavatest.dto.Item;

/**
 * Created by mfeldsztejn on 9/10/16.
 */

public class LoadingViewHolder<T extends Item> extends ItemViewHolder<T> {
    public LoadingViewHolder(View itemView) {
        super(itemView, null);
    }

    @Override
    public void bind(T item) {
        //Do nothing
    }

    @Override
    protected String getDescriptionText(T i) {
        return null;
    }

    @Override
    protected View.OnClickListener getClickListener(T i) {
        return null;
    }
}
