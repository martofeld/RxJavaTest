package com.mfeldsztejn.rxjavatest.main.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.Item;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;


/**
 * Created by mfeldsztejn on 9/6/16.
 */

public abstract class ItemViewHolder<T extends Item> extends RecyclerView.ViewHolder {

    protected OnItemClickListener listener;
    private TextView nameTv;
    private TextView descriptionTv;

    public ItemViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView);
        nameTv = (TextView) itemView.findViewById(R.id.people_list_item_name);
        descriptionTv = (TextView) itemView.findViewById(R.id.people_list_item_description);
        this.listener = listener;
    }

    public void bind(T item){
        nameTv.setText(item.getName());
        descriptionTv.setText(getDescriptionText(item));
        itemView.setOnClickListener(getClickListener(item));
    }

    protected abstract String getDescriptionText(T i);
    protected abstract View.OnClickListener getClickListener(T i);
}
