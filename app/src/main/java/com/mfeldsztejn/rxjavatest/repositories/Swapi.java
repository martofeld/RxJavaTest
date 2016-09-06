package com.mfeldsztejn.rxjavatest.repositories;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mfeldsztejn on 9/5/16.
 */

public class Swapi {

    private static final String BASE_URL = "http://swapi.co/api/";
    private static Swapi instance;
    private Retrofit retrofit;

    private Swapi() {
        createRetrofit();
    }

    public static Swapi getInstance() {
        if(instance == null){
            instance = new Swapi();
        }
        return instance;
    }

    private void createRetrofit() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL).build();
    }

    public  <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }


}
