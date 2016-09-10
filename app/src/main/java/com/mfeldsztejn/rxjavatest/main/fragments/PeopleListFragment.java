package com.mfeldsztejn.rxjavatest.main.fragments;

import android.os.Bundle;
import android.text.TextUtils;

import com.mfeldsztejn.rxjavatest.dto.People;
import com.mfeldsztejn.rxjavatest.main.adapters.PeopleAdapter;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;

import rx.Observable;

/**
 * Created by mfeldsztejn on 9/5/16.
 */

public class PeopleListFragment extends BaseListFragment<People> {

    private PeopleAdapter adapter;
    private People people;
    private int page = 1;

    public PeopleListFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestData();
    }

    @Override
    protected Observable<People> getRequestObservable() {
        return service.getPeople(page);
    }

    @Override
    protected void onRequestSuccess(People people) {
        this.people = people;
        if (adapter == null) {
            this.adapter = new PeopleAdapter(people, (OnItemClickListener) getActivity());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.addAll(people.getPeople());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onScrolledEnough() {
        if (!isLoading && !TextUtils.isEmpty(people.getNext())) {
            page++;
            requestData();
        }
    }
}
