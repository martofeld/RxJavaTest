package com.mfeldsztejn.rxjavatest.main.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.People;
import com.mfeldsztejn.rxjavatest.dto.Person;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;
import com.mfeldsztejn.rxjavatest.main.viewholders.ItemViewHolder;
import com.mfeldsztejn.rxjavatest.main.viewholders.PeopleViewHolder;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class PeopleAdapter extends ItemAdapter<Person> {

    public PeopleAdapter(People values, OnItemClickListener listener){
        super(values.getPeople(), listener);
    }

    protected ItemViewHolder<Person> getViewHolder(ViewGroup parent) {
        return new PeopleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false), listener);
    }
}
