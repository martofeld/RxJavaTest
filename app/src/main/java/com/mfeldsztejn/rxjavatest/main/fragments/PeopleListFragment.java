package com.mfeldsztejn.rxjavatest.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
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

    private static final int PAGE_SIZE = 10;
    private RecyclerView recyclerView;
    private PeopleAdapter adapter;
    private boolean isLoading;
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

    private void requestData() {
        isLoading = true;
        Observable<People> peopleList = service.getPeople(page);
        awaitForView(peopleList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction, onErrorAction);
    }

    @Override
    protected void onSuccess(People people) {
        isLoading = false;
        this.people = people;
        if (adapter == null) {
            this.adapter = new PeopleAdapter(people, (OnItemClickListener) getActivity());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.addAll(people.getPeople());
            adapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !TextUtils.isEmpty(people.getNext())) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        page++;
                        requestData();
                    }
                }
            }
        });
        subject.onNext(recyclerView);
        return v;
    }
}
