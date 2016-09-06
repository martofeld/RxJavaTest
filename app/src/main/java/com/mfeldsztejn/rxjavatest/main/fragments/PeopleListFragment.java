package com.mfeldsztejn.rxjavatest.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.People;
import com.mfeldsztejn.rxjavatest.main.fragments.adapters.PeopleAdapter;
import com.mfeldsztejn.rxjavatest.repositories.Swapi;
import com.mfeldsztejn.rxjavatest.repositories.services.ItemService;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by mfeldsztejn on 9/5/16.
 */

public class PeopleListFragment extends Fragment {
    PublishSubject<RecyclerView> subject = PublishSubject.create();

    private RecyclerView recyclerView;
    private PeopleAdapter adapter;

    public PeopleListFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemService service = Swapi.getInstance().create(ItemService.class);
        Observable<People> peopleList = service.getPeople();
        awaitForView(peopleList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<People>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(People people) {
                Log.d("TAG", people.toString());
                adapter = new PeopleAdapter(people);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    protected <T> Observable<T> awaitForView(final Observable<T> observable) {
        return Observable
                .combineLatest(subject, observable, new Func2<RecyclerView, T, Pair<RecyclerView, T>>() {
                    @Override
                    public Pair<RecyclerView, T> call(final RecyclerView view, final T result) {
                        return new Pair<>(view, result);
                    }
                }).skipWhile(new Func1<Pair<RecyclerView, T>, Boolean>() {
                    @Override
                    public Boolean call(final Pair<RecyclerView, T> pair) {
                        return pair.first == null;
                    }
                }).map(new Func1<Pair<RecyclerView, T>, T>() {
                    @Override
                    public T call(final Pair<RecyclerView, T> pair) {
                        return pair.second;
                    }
                }).first();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_people, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        subject.onNext(recyclerView);
        return v;
    }
}
