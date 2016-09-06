package com.mfeldsztejn.rxjavatest.main.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.Person;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class PeopleViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTv;
    private TextView descriptionTv;
    private OnItemClickListener listener;

    public PeopleViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView);
        nameTv = (TextView) itemView.findViewById(R.id.people_list_item_name);
        descriptionTv = (TextView) itemView.findViewById(R.id.people_list_item_description);
        this.listener = listener;
    }

    public void bind(final Person person) {
        nameTv.setText(person.getName());
        String description = itemView.getContext().getString(R.string.person_details, person.getHeight(), person.getMass(), person.getBirthYear());
        descriptionTv.setText(description);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPersonSelected(person);
            }
        });
    }
}
