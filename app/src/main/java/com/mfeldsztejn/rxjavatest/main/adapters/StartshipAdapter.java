package com.mfeldsztejn.rxjavatest.main.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.Starship;
import com.mfeldsztejn.rxjavatest.dto.Starships;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;
import com.mfeldsztejn.rxjavatest.main.viewholders.ItemViewHolder;
import com.mfeldsztejn.rxjavatest.main.viewholders.StarshipsViewHolder;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class StartshipAdapter extends ItemAdapter<Starship> {

    public StartshipAdapter(Starships starships, OnItemClickListener listener) {
        super(starships.getStarships(), listener);
    }

    @Override
    protected ItemViewHolder<Starship> getViewHolder(ViewGroup parent) {
        return new StarshipsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false), listener);
    }
}
