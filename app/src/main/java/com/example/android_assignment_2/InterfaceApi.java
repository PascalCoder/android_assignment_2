package com.example.android_assignment_2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceApi {

    @GET("search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<ArtistList> getRockArtists();

    @GET("search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<ArtistList> getClassicArtists();

    @GET("search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<ArtistList> getPopArtists();
}
