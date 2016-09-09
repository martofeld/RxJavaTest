package com.mfeldsztejn.rxjavatest.repositories.services;

import com.mfeldsztejn.rxjavatest.dto.People;
import com.mfeldsztejn.rxjavatest.dto.Starships;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mfeldsztejn on 9/5/16.
 */

public interface ItemService {
    @GET("people")
    Observable<People> getPeople(@Query("page") int page);

    @GET("starships")
    Observable<Starships> getStartships();
}
