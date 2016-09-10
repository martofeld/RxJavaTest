package com.mfeldsztejn.rxjavatest.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.Item;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;
import com.mfeldsztejn.rxjavatest.main.viewholders.ItemViewHolder;
import com.mfeldsztejn.rxjavatest.main.viewholders.LoadingViewHolder;

import java.util.List;

/**
 * Created by mfeldsztejn on 9/10/16.
 */

public abstract class ItemAdapter<I extends Item> extends RecyclerView.Adapter<ItemViewHolder<I>> {

    protected static final int ITEM_VIEW_HOLDER = 1;
    protected static final int LOADING_VIEW_HOLDER = 2;
    protected final OnItemClickListener listener;
    protected List<I> values;
    private boolean isLoading;

    public ItemAdapter(List<I> values, OnItemClickListener listener) {
        this.values = values;
        this.listener = listener;
    }

    @Override
    public ItemViewHolder<I> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder<I> viewHolder;
        if (viewType == LOADING_VIEW_HOLDER) {
            viewHolder = new LoadingViewHolder<>(LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false));
        } else {
            viewHolder = getViewHolder(parent);
        }
        return viewHolder;
    }

    protected abstract ItemViewHolder<I> getViewHolder(ViewGroup parent);

    @Override
    public void onBindViewHolder(ItemViewHolder<I> holder, int position) {
        if (holder.getItemViewType() == ITEM_VIEW_HOLDER) {
            holder.bind(values.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = ITEM_VIEW_HOLDER;
        if (position == getItemCount() - 1 && isLoading) {
            viewType = LOADING_VIEW_HOLDER;
        }
        return viewType;
    }

    @Override
    public int getItemCount() {
        int count = values.size();
        if (isLoading)
            count++;
        return count;
    }

    public void addAll(List<I> newValues) {
        values.addAll(newValues);
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
        if (isLoading)
            notifyItemInserted(getItemCount());
        else
            notifyItemRemoved(getItemCount() - 1);
    }
}
