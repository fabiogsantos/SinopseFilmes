package com.tecdam.fabiogsantos.sinopsefilmes.ui;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tecdam.fabiogsantos.sinopsefilmes.R;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Movie;
import com.tecdam.fabiogsantos.sinopsefilmes.repository.api.NotificationDownload;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String ARG_MOVIE = "Movie";

    private String urlbackdrop;
    private String urlDownload;
    DownloadManager mDownloadManager;
    CompleteDownload mCompleteDownload;

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
        urlDownload = getString(R.string.url_themoviedb_image_origin);

        mDownloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

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

    public void onDownloadImage(View view) {
        // Solicita o download da imagem
        DownloadManager.Request mRqRequest = new DownloadManager.Request(
                Uri.parse(urlDownload + mMovie.backdrop_path));
        mRqRequest.setDescription(mMovie.title);
        mDownloadManager.enqueue(mRqRequest);

        // Escuta resposta da concluisão do download para enviar uma notificação para o usuario
        if (mCompleteDownload == null) {
            mCompleteDownload = new CompleteDownload();
            registerReceiver(mCompleteDownload, new IntentFilter(
                    DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mCompleteDownload);
    }

    private class CompleteDownload extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(
                    DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {

                // Envia a notificação para usuario informado sobre a conclusão do download
                NotificationDownload notif = new NotificationDownload();
                notif.sendNotification(context,getString(R.string.app_label),getString(R.string.notification_download));
            }
        }
    }
}