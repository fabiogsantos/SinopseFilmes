package com.tecdam.fabiogsantos.sinopsefilmes.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by fabio.goncalves on 02/10/2017.
 */

public class Genre implements Serializable {
    @SerializedName("id")
    int mId;
    @SerializedName("name")
    String mName;

    public Genre(int mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
