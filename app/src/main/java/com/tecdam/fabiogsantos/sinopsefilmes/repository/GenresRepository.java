package com.tecdam.fabiogsantos.sinopsefilmes.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;
import com.tecdam.fabiogsantos.sinopsefilmes.repository.api.TheMovieServiceClient;
import com.tecdam.fabiogsantos.sinopsefilmes.repository.api.TheMovieWebService;

/**
 * Created by fabio.goncalves on 05/10/2017.
 */

public class GenresRepository {
    private TheMovieWebService theMovieWebService = TheMovieServiceClient.getTheMovieServiceClient().create(TheMovieWebService.class);

    public LiveData<Genres> getGenres() {
        final MutableLiveData<Genres> mGenres = new MutableLiveData<>();

        PAROU AQUI
                CONSULTAR DE
        C:\Users\fabio.goncalves\Documents\TECDAM\Android\Android-Course-master\ArchitectureComponents-master\app\src\main\java\br\com\dfn\androidcomponent\architecturecomponents\repository
                FilmsRepository.JAVA
    }
}
