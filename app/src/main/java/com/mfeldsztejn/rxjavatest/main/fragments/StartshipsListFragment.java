package com.mfeldsztejn.rxjavatest.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mfeldsztejn.rxjavatest.dto.Starships;
import com.mfeldsztejn.rxjavatest.main.adapters.StartshipAdapter;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;

import rx.Observable;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class StartshipsListFragment extends BaseListFragment<Starships> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestData();
    }

    @Override
    protected void onRequestSuccess(Starships starships) {
        recyclerView.setAdapter(new StartshipAdapter(starships, (OnItemClickListener) getActivity()));
    }

    @Override
    protected Observable<Starships> getRequestObservable() {
        return service.getStartships();
    }

    @Override
    public void onScrolledEnough() {

    }
}
