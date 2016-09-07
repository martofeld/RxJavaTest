package com.mfeldsztejn.rxjavatest.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.People;
import com.mfeldsztejn.rxjavatest.main.adapters.PeopleAdapter;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mfeldsztejn on 9/5/16.
 */

public class PeopleListFragment extends BaseFragment<People> {

    private RecyclerView recyclerView;

    public PeopleListFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable<People> peopleList = service.getPeople();
        awaitForView(peopleList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction, onErrorAction);
    }

    @Override
    protected void onSuccess(People people) {
        super.onSuccess(people);
        recyclerView.setAdapter(new PeopleAdapter(people, (OnItemClickListener) getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        subject.onNext(recyclerView);
        return v;
    }
}
