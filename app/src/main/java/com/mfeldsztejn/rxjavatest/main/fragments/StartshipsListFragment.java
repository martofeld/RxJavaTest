package com.mfeldsztejn.rxjavatest.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.mfeldsztejn.rxjavatest.dto.StarShips;
import com.mfeldsztejn.rxjavatest.main.adapters.StarShipAdapter;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;

import rx.Observable;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class StartShipsListFragment extends BaseListFragment<StarShips> {
    private StarShipAdapter adapter;
    private StarShips starShips;
    private int page = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestData();
    }

    @Override
    protected void onRequestSuccess(StarShips starShips) {
        this.starShips = starShips;
        if (adapter == null) {
            adapter = new StarShipAdapter(starShips, (OnItemClickListener) getContext());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.addAll(starShips.getStarShips());
            adapter.setLoading(false);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected Observable<StarShips> getRequestObservable() {
        return service.getStartShips(page);
    }

    @Override
    public void onScrolledEnough() {
        if (!isLoading && !TextUtils.isEmpty(starShips.getNext())) {
            page++;
            adapter.setLoading(true);
            requestData();
        }
    }
}
