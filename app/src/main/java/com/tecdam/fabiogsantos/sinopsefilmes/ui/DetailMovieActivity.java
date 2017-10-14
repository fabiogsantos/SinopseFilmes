package com.tecdam.fabiogsantos.sinopsefilmes.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tecdam.fabiogsantos.sinopsefilmes.R;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Movie;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String ARG_MOVIE = "Movie";

    private String urlbackdrop;

    private Movie mMovie;
    private ImageView imageViewDetailBackDropMovie;
    private TextView textViewDetailTitleMovie;
    private TextView textViewDetailInfoMovie;
    private TextView textViewDetailSynopseMovie;

    public static void showActivite(Context context, Movie movie) {
        Intent intent = new Intent();
        intent.setClass(context, DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.ARG_MOVIE,movie);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        urlbackdrop = getString(R.string.url_themoviedb_image_medium);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            mMovie = (Movie) bundle.getSerializable(ARG_MOVIE);
        }

        ActionBar actionBar = getSupportActionBar();
        // habilita o botão de voltar na tela
        actionBar.setDisplayHomeAsUpEnabled(true);


        subscribeUi();

    }

    private void subscribeUi() {
        imageViewDetailBackDropMovie = (ImageView) findViewById(R.id.imageViewDetailBackDropMovie);
        textViewDetailTitleMovie = (TextView) findViewById(R.id.textViewDetailTitleMovie);
        textViewDetailInfoMovie = (TextView) findViewById(R.id.textViewDetailInfoMovie);
        textViewDetailSynopseMovie = (TextView) findViewById(R.id.textViewDetailSynopseMovie);

        textViewDetailTitleMovie.setText(mMovie.title);
        textViewDetailInfoMovie.setText(getString(R.string.movie_release_date) + ": " + mMovie.release_date + "  " +
                getString(R.string.movie_vote_average) + ": " + String.valueOf(mMovie.vote_average) + "  " +
                getString(R.string.movie_vote_count) + ": " + String.valueOf(mMovie.vote_count) + "  " +
                getString(R.string.movie_popularity) + ": " + String.valueOf(mMovie.popularity));
        textViewDetailSynopseMovie.setText(mMovie.overview);

        Picasso.with(this)
                .load(urlbackdrop + mMovie.backdrop_path)
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading_error)
                .into(imageViewDetailBackDropMovie);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}