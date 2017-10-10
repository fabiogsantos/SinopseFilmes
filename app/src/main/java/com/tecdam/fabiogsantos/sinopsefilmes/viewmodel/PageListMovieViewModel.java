package com.tecdam.fabiogsantos.sinopsefilmes.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.tecdam.fabiogsantos.sinopsefilmes.model.PageListMovie;
import com.tecdam.fabiogsantos.sinopsefilmes.repository.PageListMovieRepository;

/**
 * Created by fabio.goncalves on 10/10/2017.
 */

public class PageListMovieViewModel extends ViewModel {
    private LiveData<PageListMovie> mPageListMovie;
    private PageListMovieRepository mPageListMovieRepository = new PageListMovieRepository();

    public void init(String apiKey,
                     String language,
                     String region,
                     String sortby,
                     boolean includeAdult,
                     boolean includeVideo,
                     int page,
                     String withGenres) {
        if(mPageListMovie != null){
            return;
        }
        mPageListMovie = mPageListMovieRepository.getPageListMovie(apiKey,
                                                                   language,
                                                                   region,
                                                                   sortby,
                                                                   includeAdult,
                                                                   includeVideo,
                                                                   page,
                                                                   withGenres);
    }

    public void refresh(String apiKey,
                        String language,
                        String region,
                        String sortby,
                        boolean includeAdult,
                        boolean includeVideo,
                        int page,
                        String withGenres) {
        mPageListMovie = null; // libera referencia na memoria e limpa a tela atraves do metodo observe
        mPageListMovie = mPageListMovieRepository.getPageListMovie(apiKey,
                                                                    language,
                                                                    region,
                                                                    sortby,
                                                                    includeAdult,
                                                                    includeVideo,
                                                                    page,
                                                                    withGenres); // Carrega novamente a lista
    }

    public LiveData<PageListMovie> getGenres() {
        return this.mPageListMovie;
    }
}
