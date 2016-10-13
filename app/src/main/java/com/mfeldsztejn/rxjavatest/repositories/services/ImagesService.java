package com.mfeldsztejn.rxjavatest.repositories.services;

import com.mfeldsztejn.rxjavatest.dto.Images;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mfeldsztejn on 9/14/16.
 */

public interface ImagesService {
    @GET("images?embed_content_only=true&exclude_nudity=true&file_types=jpg%2Cpng&orientations=Horizontal")
    Observable<Images> getImage(@Header("Api-Key") String apiKey, @Query("phrase") String query);
}
