package com.mfeldsztejn.rxjavatest.main.viewholders;

import android.view.View;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.Person;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class PeopleViewHolder extends ItemViewHolder<Person> {

    public PeopleViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    @Override
    protected String getDescriptionText(Person person) {
        return itemView.getContext().getString(R.string.person_details, person.getHeight(), person.getMass(), person.getBirthYear());
    }

    @Override
    protected View.OnClickListener getClickListener(final Person person) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPersonSelected(person);
            }
        };
    }

}
