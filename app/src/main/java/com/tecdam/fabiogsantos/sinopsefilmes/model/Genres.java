package com.tecdam.fabiogsantos.sinopsefilmes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabio.goncalves on 03/10/2017.
 */

public class Genres /*implements Serializable*/ {
    public List<Genre> genres = new ArrayList<>();

    public class Genre {
        public int id;
        public String name;

        public Genre(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public Genre AddGenre(int id, String name) {
        Genre genre = new Genre(id, name);

        if (genres == null) {
            genres = new ArrayList<>();
        }

        genres.add(genre);

        return genre;
    }
}
