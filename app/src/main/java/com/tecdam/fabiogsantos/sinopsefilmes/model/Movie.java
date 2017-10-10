package com.tecdam.fabiogsantos.sinopsefilmes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabio.goncalves on 10/10/2017.
 */

public class Movie implements Serializable {
    public int vote_count;
    public int id;
    public boolean video;
    public double vote_average;
    public String title;
    public double popularity;
    public String poster_path;
    public String original_language;
    public String original_title;
    public List<Integer> genre_ids = new ArrayList<>();
    public String backdrop_path;
    public boolean adult;
    public String overview;
    public String release_date;
}
