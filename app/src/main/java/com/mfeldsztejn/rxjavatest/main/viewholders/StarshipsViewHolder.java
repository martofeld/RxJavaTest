package com.mfeldsztejn.rxjavatest.main.viewholders;

import android.view.View;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.StarShip;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class StarshipsViewHolder extends ItemViewHolder<StarShip> {
    public StarshipsViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    @Override
    protected String getDescriptionText(StarShip i) {
        return itemView.getContext().getString(R.string.starship_details, i.getModel(), i.getManufacturer());
    }

    @Override
    protected View.OnClickListener getClickListener(final StarShip i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onStarShipSelected(i);
            }
        };
    }
}
