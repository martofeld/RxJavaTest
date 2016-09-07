package com.mfeldsztejn.rxjavatest.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;

import com.mfeldsztejn.rxjavatest.repositories.Swapi;
import com.mfeldsztejn.rxjavatest.repositories.services.ItemService;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.PublishSubject;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public class BaseFragment<I> extends Fragment {
    protected PublishSubject<RecyclerView> subject = PublishSubject.create();
    protected ItemService service;
    protected Action1<I> onNextAction;
    protected Action1<Throwable> onErrorAction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = Swapi.getInstance().create(ItemService.class);
        onNextAction = new Action1<I>() {
            @Override
            public void call(I i) {
                onSuccess(i);
            }
        };
        onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable i) {
                onError(i);
            }
        };
    }

    protected void onSuccess(I i){
        Log.d("TAG", i.toString());
    }

    protected void onError(Throwable t){
        t.printStackTrace();
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
}
