package com.mfeldsztejn.rxjavatest.repositories;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .baseUrl(BASE_URL)
                .build();
    }

    public  <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }


}
