package com.tecdam.fabiogsantos.sinopsefilmes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabio.goncalves on 09/10/2017.
 */

public class Configuration implements Serializable {
    public ConfigImage images = new ConfigImage();

    public class ConfigImage {
        public String base_url;
        public String secure_base_url;
    }
}
