package com.mfeldsztejn.rxjavatest.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.People;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;
import com.mfeldsztejn.rxjavatest.main.viewholders.PeopleViewHolder;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder> {
    private People values;
    private OnItemClickListener listener;

    public PeopleAdapter(People values, OnItemClickListener listener){
        this.values = values;
        this.listener = listener;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PeopleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.people_list_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        holder.bind(values.getPeople().get(position));
    }

    @Override
    public int getItemCount() {
        return values.getPeople().size();
    }
}
