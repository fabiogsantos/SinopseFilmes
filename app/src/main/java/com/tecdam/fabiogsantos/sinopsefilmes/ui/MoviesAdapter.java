package com.tecdam.fabiogsantos.sinopsefilmes.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tecdam.fabiogsantos.sinopsefilmes.R;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Movie;

import java.util.List;

/**
 * Created by fabio.goncalves on 11/10/2017.
 */

public class MoviesAdapter extends ArrayAdapter<Movie> {

    private Context context;
    private String urlPoster;

    public MoviesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.urlPoster = context.getString(R.string.url_themoviedb_image_small);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.view_listmovie, parent, false);
        }

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        textViewTitle.setText(movie.title);

        TextView textViewInfoMovire = convertView.findViewById(R.id.textViewInfoMovie);
        textViewInfoMovire.setText(context.getString(R.string.movie_release_date)+": "+movie.release_date+"  "+
                context.getString(R.string.movie_vote_average)+": "+String.valueOf(movie.vote_average)+"  "+
                context.getString(R.string.movie_vote_count)+": "+String.valueOf(movie.vote_count)+"  "+
                context.getString(R.string.movie_popularity)+": "+String.valueOf(movie.popularity));

        TextView editTextSinopse = convertView.findViewById(R.id.editTextSinopse);
        editTextSinopse.setText(movie.overview);

        ImageView imageViewMovie = convertView.findViewById(R.id.imageViewMovie);

        Picasso.with(context)
            .load(urlPoster+movie.poster_path)
            .placeholder(R.drawable.loading)
            .error(R.drawable.loading_error)
            .into(imageViewMovie);

        /*
        // Usado apenas Para depuração do Picasso
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.listener(new Picasso.Listener()
        {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                //exception.printStackTrace();
                Log.e("Picasso",exception.getMessage(),exception.fillInStackTrace());
            }
        });
        builder.build().load(urlPoster+movie.poster_path).into(imageViewMovie);*/

        return convertView;
    }
}
