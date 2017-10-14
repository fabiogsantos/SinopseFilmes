package com.tecdam.fabiogsantos.sinopsefilmes.ui;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.tecdam.fabiogsantos.sinopsefilmes.R;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Movie;

public class MainActivity extends AppCompatActivity implements GenresFragment.OnGenresSelectedListener, ListMovieFragment.OnMoviesSelectedListener, LifecycleRegistryOwner, LifecycleObserver {

    private static final String KEY_STATE_ACTIVICE = "currentGenre";
    private static final String BACK_STACK_MOVIE = "movie";

    FragmentManager fragmentManager = getSupportFragmentManager();

    LifecycleRegistry lifecycleRegistrye = new LifecycleRegistry(this);

    private Genres.Genre mCurrentGenre;
    private Boolean mDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Verifica se existe dois paineis
        View frameListMovie = this.findViewById(R.id.frameListMovie);
        mDualPane = ((frameListMovie != null) && (frameListMovie.getVisibility() == View.VISIBLE));

        // Se ja existe uma instancia da activete entao resgata seu status
        if (savedInstanceState != null) {
            mCurrentGenre = (Genres.Genre) savedInstanceState.getSerializable(KEY_STATE_ACTIVICE);
        }

        // Se existe dois paineis entao mostra a lista de filmes do ultimo gênero selecionado
        if (mDualPane) {
          showListMovie(mCurrentGenre);
        }

        // Configura o objeto que monitora a mudança de estado da activite
        getLifecycle().addObserver(this);
    }

    // Recebe o click da lista de gênero dos filmes e
    // Carrega na UI o fragment com a lista de filmes
    @Override
    public void onGenreSelected(Genres.Genre genre) {
        showListMovie(genre);
    }

    // Recebe o click da lista de filmes e
    // Carrega na UI a activite com os detalhes do filme selecionado
    @Override
    public void onMovieSelected(Movie movie) {
        showDetailMovie(movie);
    }

    // Carrega na UI o fragment com a lista de filmes conforme o gênero selecionado
    public void showListMovie(Genres.Genre genre) {
        mCurrentGenre = genre;

        if (genre == null) {
            return;
        }

        if (mDualPane) {
            ListMovieFragment listMovieFragment = (ListMovieFragment) fragmentManager.findFragmentById(R.id.frameListMovie);
            if ((listMovieFragment == null) || (listMovieFragment.getGenre().id != genre.id)) {
                listMovieFragment = ListMovieFragment.newInstance(genre);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameListMovie,listMovieFragment);
                //fragmentTransaction.addToBackStack(BACK_STACK_MOVIE); // Nos testes verifiquei que não necessita de back stack
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(this, ListMovieActivity.class);
            intent.putExtra(ListMovieFragment.ARG_GENRE,genre);
            startActivity(intent);
        }
    }

    private void showDetailMovie(Movie movie) {
        if (movie == null) {
            return;
        }
        DetailMovieActivity.showActivite(this,movie);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_STATE_ACTIVICE,mCurrentGenre);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistrye;
    }

}
