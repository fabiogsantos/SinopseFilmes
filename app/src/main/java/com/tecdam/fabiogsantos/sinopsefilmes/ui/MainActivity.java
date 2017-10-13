package com.tecdam.fabiogsantos.sinopsefilmes.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.tecdam.fabiogsantos.sinopsefilmes.R;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;
import com.tecdam.fabiogsantos.sinopsefilmes.model.PageListMovie;
import com.tecdam.fabiogsantos.sinopsefilmes.viewmodel.PageListMovieViewModel;

public class MainActivity extends AppCompatActivity implements GenresFragment.OnGenresSelectedListener, LifecycleRegistryOwner, LifecycleObserver {

    LifecycleRegistry lifecycleRegistrye = new LifecycleRegistry(this);

    private PageListMovieViewModel pageListMovieViewModel;
    private ListView listMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listMovies = findViewById(R.id.lstViewMovies);

        pageListMovieViewModel = ViewModelProviders.of(this).get(PageListMovieViewModel.class);
        pageListMovieViewModel.init(getString(R.string.apikey),
                getString(R.string.language),
                getString(R.string.region),
                getString(R.string.sortlistmovie),
                false,
                false,
                1,
                "35");

        // Carrega os dados da View por meio do WebService com LiveData
        subscribeUi_ListMovies(pageListMovieViewModel);

        // Configura o objeto que monitora a mudança de estado da activite
        getLifecycle().addObserver(this);
    }

    private void subscribeUi_ListMovies(PageListMovieViewModel pageListMovieViewModel) {
        // Update the list when the data changes
        pageListMovieViewModel.getPageListMovie().observe(this, new Observer<PageListMovie>() {
            @Override
            public void onChanged(@Nullable PageListMovie pageListMovie) {
                if (pageListMovie != null) {
                    listMovies.setAdapter(new MoviesAdapter(getApplicationContext(),R.layout.view_listmovie, pageListMovie.results));
                }
            }
        });
    }

    @Override
    public void onGenreSelected(Genres.Genre genre) {

    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistrye;
    }

}
