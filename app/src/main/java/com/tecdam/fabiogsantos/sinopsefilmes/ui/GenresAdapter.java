package com.tecdam.fabiogsantos.sinopsefilmes.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tecdam.fabiogsantos.sinopsefilmes.R;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;

import java.util.List;

/**
 * Created by fabio.goncalves on 07/10/2017.
 */

public class GenresAdapter extends ArrayAdapter<Genres.Genre> {

    public GenresAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Genres.Genre> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Genres.Genre genre = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.view_genre, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.textViewGenre);
        textView.setText(genre.name);

        return convertView;
    }
}
