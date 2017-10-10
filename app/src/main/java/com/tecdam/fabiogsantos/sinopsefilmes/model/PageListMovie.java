package com.tecdam.fabiogsantos.sinopsefilmes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabio.goncalves on 09/10/2017.
 */

public class PageListMovie implements Serializable {
    public int page;
    public int total_results;
    public int total_pages;
    public List<Movie> results = new ArrayList<>();
}
