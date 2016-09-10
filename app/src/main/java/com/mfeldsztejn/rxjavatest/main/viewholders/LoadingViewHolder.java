package com.mfeldsztejn.rxjavatest.main.viewholders;

import android.view.View;

import com.mfeldsztejn.rxjavatest.dto.Item;

/**
 * Created by mfeldsztejn on 9/10/16.
 */

public class LoadingViewHolder<I extends Item> extends ItemViewHolder<I> {
    public LoadingViewHolder(View itemView) {
        super(itemView, null);
    }

    @Override
    public void bind(I item) {
        //Do nothing
    }

    @Override
    protected String getDescriptionText(I i) {
        return null;
    }

    @Override
    protected View.OnClickListener getClickListener(I i) {
        return null;
    }
}
