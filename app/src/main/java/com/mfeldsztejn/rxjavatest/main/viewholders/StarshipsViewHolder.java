package com.mfeldsztejn.rxjavatest.main.viewholders;

import android.view.View;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.Starship;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class StarshipsViewHolder extends ItemViewHolder<Starship> {
    public StarshipsViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    @Override
    protected String getDescriptionText(Starship i) {
        return itemView.getContext().getString(R.string.starship_details, i.getModel(), i.getManufacturer());
    }

    @Override
    protected View.OnClickListener getClickListener(final Starship i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onStarshipSelected(i);
            }
        };
    }
}
