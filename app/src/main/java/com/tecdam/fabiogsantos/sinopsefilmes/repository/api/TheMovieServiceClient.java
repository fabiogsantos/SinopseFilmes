package com.tecdam.fabiogsantos.sinopsefilmes.repository.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fabio.goncalves on 01/10/2017.
 */

public class TheMovieServiceClient {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getTheMovieServiceClient() {
        return retrofit;
    }

}
