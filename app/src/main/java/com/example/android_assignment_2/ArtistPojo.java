package com.example.android_assignment_2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class ArtistPojo {

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("collectionName")
    @Expose
    private String collectionName;

    @SerializedName("artworkUrl60")
    @Expose
    private String artWorkUrl60;

    @SerializedName("trackPrice")
    @Expose
    private String trackPrice;

    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getArtWorkUrl60() {
        return artWorkUrl60;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setArtWorkUrl60(String artWorkUrl60) {
        this.artWorkUrl60 = artWorkUrl60;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}