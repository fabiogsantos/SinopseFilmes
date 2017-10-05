package com.tecdam.fabiogsantos.sinopsefilmes.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabio.goncalves on 03/10/2017.
 */

public class Genres implements Serializable {
    @SerializedName("Genres")
    List<Genre> mGenres;

    public Genres(List<Genre> mGenres) {
        this.mGenres = mGenres;
    }

    public List<Genre> getmGenres() {
        return mGenres;
    }

    public void setmGenres(List<Genre> mGenres) {
        this.mGenres = mGenres;
    }
}
