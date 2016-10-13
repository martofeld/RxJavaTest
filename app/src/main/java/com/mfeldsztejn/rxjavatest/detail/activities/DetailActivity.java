package com.mfeldsztejn.rxjavatest.detail.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mfeldsztejn.rxjavatest.dto.Images;
import com.mfeldsztejn.rxjavatest.repositories.Constants;
import com.mfeldsztejn.rxjavatest.repositories.ImageApi;
import com.mfeldsztejn.rxjavatest.repositories.services.ImagesService;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by mfeldsztejn on 9/14/16.
 */

public abstract class DetailActivity extends AppCompatActivity {
    protected ImagesService service;
    private Action1<Images> onNextAction;
    private Action1<Throwable> onErrorAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ImageApi.getInstance().create(ImagesService.class);
        onNextAction = new Action1<Images>() {
            @Override
            public void call(Images t) {
                onSuccess(t);
            }
        };
        onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        };
    }

    abstract void onSuccess(Images images);

    protected void getData(String query) {
        query = query.replace(" ", "+") + "+star+wars";
        service.getImage(Constants.GETTY_API_KEY, query)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(onNextAction, onErrorAction);
    }
}
