package com.mfeldsztejn.rxjavatest.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.People;
import com.mfeldsztejn.rxjavatest.dto.Person;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;
import com.mfeldsztejn.rxjavatest.main.viewholders.ItemViewHolder;
import com.mfeldsztejn.rxjavatest.main.viewholders.LoadingViewHolder;
import com.mfeldsztejn.rxjavatest.main.viewholders.PeopleViewHolder;

import java.util.List;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class PeopleAdapter extends RecyclerView.Adapter<ItemViewHolder<Person>> {

    private static final int ITEM_VIEW_HOLDER = 1;
    private static final int LOADING_VIEW_HOLDER = 2;
    private List<Person> values;
    private OnItemClickListener listener;
    private boolean isLoading;

    public PeopleAdapter(People values, OnItemClickListener listener){
        this.values = values.getPeople();
        this.listener = listener;
    }

    @Override
    public ItemViewHolder<Person> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder<Person> viewHolder;
        if (viewType == LOADING_VIEW_HOLDER) {
            viewHolder = new LoadingViewHolder<>(LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false));
        } else {
            viewHolder = new PeopleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false), listener);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder<Person> holder, int position) {
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

    public void addAll(List<Person> people) {
        values.addAll(people);
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
        if (isLoading)
            notifyItemInserted(getItemCount());
        else
            notifyItemRemoved(getItemCount() - 1);
    }
}
