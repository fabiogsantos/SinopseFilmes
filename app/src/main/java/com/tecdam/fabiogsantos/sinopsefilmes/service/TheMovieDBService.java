package com.tecdam.fabiogsantos.sinopsefilmes.service;

import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fabio.goncalves on 01/10/2017.
 */

public interface TheMovieDBService {

//    @GET("configuration?api_key={apiKey}")
//    Call<Configuration> configuration(@Path("apiKey") String apiK//ey);

    // Lista Gênero
    // Exemplo: https://api.themoviedb.org/3/genre/movie/list?api_key=fe650d50ffdfaf937e01c252506c5d03&language=pt-BR
    //@GET("3/genre/movie/list?api_key={apiKey}&language={language}")
    //Call<List<Genre>> listGenre(@Path("apiKey") String apiKey, @Path("language") String language);
    @GET("3/genre/movie/list?api_key={apiKey}&language={language}")
    Call<Genres> listGenre(@Path("apiKey") String apiKey, @Path("language") String language);

//    @GET("discover/movie?api_key={apiKey}&language={language}&region={region}&sort_by=popularity.desc&include_adult={adult}&include_video=false&page={page}&with_genres={genres}")
//    Call<PageListMove> pageListMovie(@Path("genres") String genres, @Path("page") int page, @Path("adult") boolean adult, @Path("apiKey") String apiKey, @Path("language") String language, @Path("region") String region);
//
//    @GET("movie/{idMovie}?api_key={apiKey}&language={language}")
//    Call<InfoMovie> infoMovie(@Path("idMovie") int idMovie, @Path("apiKey") String apiKey, @Path("language") String language);

}
