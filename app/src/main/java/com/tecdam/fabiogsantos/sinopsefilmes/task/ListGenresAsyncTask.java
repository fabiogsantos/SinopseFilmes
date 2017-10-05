package com.tecdam.fabiogsantos.sinopsefilmes.task;

import android.os.AsyncTask;
import android.util.Log;

import com.tecdam.fabiogsantos.sinopsefilmes.model.Genre;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;
import com.tecdam.fabiogsantos.sinopsefilmes.service.TheMovieDBService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fabio.goncalves on 02/10/2017.
 */

public class ListGenresAsyncTask extends AsyncTask<String, Void, Genres> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Genres doInBackground(String... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBService theMovieDBService = retrofit.create(TheMovieDBService.class);

        Call<Genres> getGenres = theMovieDBService.listGenre("fe650d50ffdfaf937e01c252506c5d03","pt-BR");

        try {
            Genres genres = getGenres.execute().body();
            for (Genre genre: genres.getmGenres()) {
                Log.i("TheMovieDBService",genre.getmName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Genres genres) {
        super.onPostExecute(genres);
    }
}
