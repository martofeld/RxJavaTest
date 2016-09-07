package com.mfeldsztejn.rxjavatest.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.Starships;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;
import com.mfeldsztejn.rxjavatest.main.viewholders.StarshipsViewHolder;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class StartshipAdapter extends RecyclerView.Adapter<StarshipsViewHolder> {

    private Starships starships;
    private OnItemClickListener listener;

    public StartshipAdapter(Starships starships, OnItemClickListener listener) {
        super();
        this.starships = starships;
        this.listener = listener;
    }

    @Override
    public StarshipsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StarshipsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(StarshipsViewHolder holder, int position) {
        holder.bind(starships.getStarships().get(position));
    }

    @Override
    public int getItemCount() {
        return starships.getStarships().size();
    }
}
