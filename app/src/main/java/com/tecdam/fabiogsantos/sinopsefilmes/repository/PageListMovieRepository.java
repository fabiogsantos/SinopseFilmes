package com.tecdam.fabiogsantos.sinopsefilmes.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.tecdam.fabiogsantos.sinopsefilmes.model.PageListMovie;
import com.tecdam.fabiogsantos.sinopsefilmes.repository.api.TheMovieServiceClient;
import com.tecdam.fabiogsantos.sinopsefilmes.repository.api.TheMovieWebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fabio.goncalves on 10/10/2017.
 */

public class PageListMovieRepository {
    private TheMovieWebService theMovieWebService = TheMovieServiceClient.getBuilderTheMovieRetrofit().create(TheMovieWebService.class);

    public LiveData<PageListMovie> getPageListMovie(String apiKey,
                                                    String language,
                                                    String region,
                                                    String sortby,
                                                    boolean includeAdult,
                                                    boolean includeVideo,
                                                    int page,
                                                    String withGenres) {
        final MutableLiveData<PageListMovie> data = new MutableLiveData<>();

        theMovieWebService.getPageListMovie(apiKey,
                                            language,
                                            region,
                                            sortby,
                                            includeAdult,
                                            includeVideo,
                                            page,
                                            withGenres)
                .enqueue(new Callback<PageListMovie>() {
                    @Override
                    public void onResponse(Call<PageListMovie> call, Response<PageListMovie> response) {
                        data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<PageListMovie> call, Throwable t) {
                        Log.i(PageListMovie.class.getSimpleName(),t.getMessage());
                    }
                });

        return data;
    }
}
