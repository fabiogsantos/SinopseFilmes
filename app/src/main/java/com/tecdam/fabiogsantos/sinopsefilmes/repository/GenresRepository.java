package com.tecdam.fabiogsantos.sinopsefilmes.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;
import com.tecdam.fabiogsantos.sinopsefilmes.repository.api.TheMovieServiceClient;
import com.tecdam.fabiogsantos.sinopsefilmes.repository.api.TheMovieWebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fabio.goncalves on 05/10/2017.
 */

public class GenresRepository {
    private TheMovieWebService theMovieWebService = TheMovieServiceClient.getBuilderTheMovieRetrofit().create(TheMovieWebService.class);

    public LiveData<Genres> getGenres(String apiKey, String language) {
        final MutableLiveData<Genres> data = new MutableLiveData<>();

        theMovieWebService.getGenres(apiKey, language).enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(Call<Genres> call, Response<Genres> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Genres> call, Throwable t) {
                Log.i(GenresRepository.class.getSimpleName(),t.getMessage());
            }
        });

        return data;
    }
}
