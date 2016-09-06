package com.mfeldsztejn.rxjavatest.main.fragments.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.Person;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class PeopleViewHolder extends RecyclerView.ViewHolder {
    public PeopleViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(Person person) {
        TextView t = (TextView) itemView.findViewById(R.id.people_list_item_description);
        t.setText(itemView.getContext().getString(R.string.person_details, person.getHeight(), person.getMass(), person.getBirthYear()));
        t = (TextView) itemView.findViewById(R.id.people_list_item_name);
        t.setText(person.getName());
    }
}
