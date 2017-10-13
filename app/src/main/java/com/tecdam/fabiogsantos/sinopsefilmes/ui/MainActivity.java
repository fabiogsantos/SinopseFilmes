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
import android.widget.TextView;
import android.widget.Toast;

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
        try {
            if (genre != null) {
                TextView textTitle = (TextView) findViewById(R.id.textTitleListMovies);
                textTitle.setText(getString(R.string.titlelistmovie)+" - "+genre.name);

                pageListMovieViewModel.refresh(getString(R.string.apikey),
                        getString(R.string.language),
                        getString(R.string.region),
                        getString(R.string.sortlistmovie),
                        false,
                        false,
                        1,
                        String.valueOf(genre.id));

                // Carrega os dados da View por meio do WebService com LiveData
                subscribeUi_ListMovies(pageListMovieViewModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistrye;
    }

}
