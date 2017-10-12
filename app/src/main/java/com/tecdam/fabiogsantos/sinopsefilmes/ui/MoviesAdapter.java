package com.tecdam.fabiogsantos.sinopsefilmes.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

    public MoviesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
        this.context = context;
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

        EditText editTextSinopse = convertView.findViewById(R.id.editTextSinopse);
        editTextSinopse.setText(movie.overview);
/*
        ImageView imageViewMovie = convertView.findViewById(R.id.imageViewMovie);

        Picasso.with(context)
            .load(context.getString(R.string.url_themoviedb_image)+movie.poster_path)
            .placeholder(R.drawable.loading)
            .error(R.drawable.loading_error)
            .into(imageViewMovie);
*/
        return convertView;
    }
}
