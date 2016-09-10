package com.mfeldsztejn.rxjavatest.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.main.listeners.PagingOnScrollListener;
import com.mfeldsztejn.rxjavatest.repositories.Swapi;
import com.mfeldsztejn.rxjavatest.repositories.services.ItemService;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public abstract class BaseListFragment<I extends Object> extends Fragment implements PagingOnScrollListener.Callback {
    protected BehaviorSubject<RecyclerView> subject = BehaviorSubject.create();
    protected ItemService service;
    protected Action1<I> onNextAction;
    protected Action1<Throwable> onErrorAction;
    protected RecyclerView recyclerView;
    protected boolean isLoading;
    private Subscription requestSubscription;

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
                onRequestError(i);
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new PagingOnScrollListener(this));
        subject.onNext(recyclerView);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cleanSubscription();
    }

    private void onSuccess(I i) {
        isLoading = false;
        cleanSubscription();
        onRequestSuccess(i);
    }

    protected void onRequestError(Throwable t) {
        t.printStackTrace();
    }

    protected Observable<I> awaitForView(final Observable<I> observable) {
        return Observable
                .combineLatest(subject, observable, new Func2<RecyclerView, I, Pair<RecyclerView, I>>() {
                    @Override
                    public Pair<RecyclerView, I> call(final RecyclerView view, final I result) {
                        return new Pair<>(view, result);
                    }
                }).skipWhile(new Func1<Pair<RecyclerView, I>, Boolean>() {
                    @Override
                    public Boolean call(final Pair<RecyclerView, I> pair) {
                        return pair.first == null;
                    }
                }).map(new Func1<Pair<RecyclerView, I>, I>() {
                    @Override
                    public I call(final Pair<RecyclerView, I> pair) {
                        return pair.second;
                    }
                }).first();
    }

    protected void requestData() {
        requestSubscription = awaitForView(getRequestObservable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction, onErrorAction);
    }

    private void cleanSubscription() {
        if (requestSubscription != null && !requestSubscription.isUnsubscribed()) {
            requestSubscription.unsubscribe();
        }
    }

    public void scrollToTop() {
        recyclerView.smoothScrollToPosition(0);
    }

    protected abstract Observable<I> getRequestObservable();

    protected abstract void onRequestSuccess(I i);
}