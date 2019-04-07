package com.example.android_assignment_2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class ArtistList {

    @SerializedName("results")
    @Expose
    public List<ArtistPojo> artistList;
}
