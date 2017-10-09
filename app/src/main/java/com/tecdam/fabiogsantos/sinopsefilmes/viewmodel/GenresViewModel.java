package com.tecdam.fabiogsantos.sinopsefilmes.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;
import com.tecdam.fabiogsantos.sinopsefilmes.repository.GenresRepository;

/**
 * Created by fabio.goncalves on 06/10/2017.
 *
 */

public class GenresViewModel extends ViewModel {

    private LiveData<Genres> mGenres;
    private GenresRepository genresRepository = new GenresRepository();

    public void init(String apiKey, String language) {
        if(mGenres != null){
            return;
        }
        mGenres = genresRepository.getGenres(apiKey, language);
    }

    public void refresh(String apiKey, String language) {
        mGenres = null; // libera referencia na memoria e limpa a tela atraves do metodo observe
        mGenres = genresRepository.getGenres(apiKey, language); // Carrega novamente a lista
    }

    public LiveData<Genres> getGenres() {
        return this.mGenres;
    }

}
