package com.tecdam.fabiogsantos.sinopsefilmes.ui;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tecdam.fabiogsantos.sinopsefilmes.R;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Movie;

public class ListMovieActivity extends AppCompatActivity implements ListMovieFragment.OnMoviesSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish();
            return;
        }

        //setContentView(R.layout.activity_list_movie);

        if (savedInstanceState == null) {
            ListMovieFragment listMovieFragment = new ListMovieFragment();
            listMovieFragment.setArguments(getIntent().getExtras());

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(android.R.id.content, listMovieFragment);
            ft.commit();
        }
    }

    @Override
    public void onMovieSelected(Movie movie) {
        Toast.makeText(this, "Movie Selected "+movie.title, Toast.LENGTH_SHORT).show();
    }
}
