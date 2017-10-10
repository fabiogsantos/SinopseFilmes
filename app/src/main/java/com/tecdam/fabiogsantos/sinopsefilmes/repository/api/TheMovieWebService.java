package com.tecdam.fabiogsantos.sinopsefilmes.repository.api;

import com.tecdam.fabiogsantos.sinopsefilmes.model.Configuration;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;
import com.tecdam.fabiogsantos.sinopsefilmes.model.PageListMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fabio.goncalves on 05/10/2017.
 */

public interface TheMovieWebService {

    @GET("configuration")
    Call<Configuration> configuration(@Query("api_key") String apiKey);

    // Lista Gênero
    // Exemplo: https://api.themoviedb.org/3/genre/movie/list?api_key=fe650d50ffdfaf937e01c252506c5d03&language=pt-BR
    @GET("genre/movie/list")
    Call<Genres> getGenres(@Query("api_key") String apiKey, @Query("language") String language);

    // Lista de filmes do gênero escolhido
    // Exemplo: https://api.themoviedb.org/3/discover/movie?api_key=fe650d50ffdfaf937e01c252506c5d03&language=pt-BR&region=BR&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=35
    @GET("discover/movie")
    Call<PageListMovie> getPageListMovie(@Query("api_key")       String apiKey,
                                         @Query("language")      String language,
                                         @Query("region")        String region,
                                         @Query("sort_by")       String sortBy,
                                         @Query("include_adult") boolean includeAdult,
                                         @Query("include_video") boolean includeVideo,
                                         @Query("page")          int page,
                                         @Query("with_genres")   String withGenres);
//
//    @GET("movie/{idMovie}?api_key={apiKey}&language={language}")
//    Call<InfoMovie> infoMovie(@Path("idMovie") int idMovie, @Path("apiKey") String apiKey, @Path("language") String language);

}
