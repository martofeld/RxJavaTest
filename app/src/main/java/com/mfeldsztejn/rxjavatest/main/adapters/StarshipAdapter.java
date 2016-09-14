package com.mfeldsztejn.rxjavatest.main.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.StarShip;
import com.mfeldsztejn.rxjavatest.dto.StarShips;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;
import com.mfeldsztejn.rxjavatest.main.viewholders.ItemViewHolder;
import com.mfeldsztejn.rxjavatest.main.viewholders.StarshipsViewHolder;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class StarShipAdapter extends ItemAdapter<StarShip> {

    public StarShipAdapter(StarShips starShips, OnItemClickListener listener) {
        super(starShips.getStarShips(), listener);
    }

    @Override
    protected ItemViewHolder<StarShip> getViewHolder(ViewGroup parent) {
        return new StarshipsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false), listener);
    }
}
