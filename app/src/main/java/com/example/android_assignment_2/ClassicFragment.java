package com.example.android_assignment_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link ClassicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassicFragment extends Fragment {

    RecyclerView recyclerView;
    InterfaceApi interfaceApi;

    public ClassicFragment() {
        // Required empty public constructor
    }

    public static ClassicFragment newInstance(){
        return new ClassicFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_classic, container, false);

        initializeRetrofit();

        recyclerView = rootView.findViewById(R.id.classic_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        getArtists();

        return rootView;
    }

    private void getArtists() {
        interfaceApi.getClassicArtists().enqueue(new Callback<ArtistList>() {
            @Override
            public void onResponse(Call<ArtistList> call, Response<ArtistList> response) {
                recyclerView.setAdapter(new CustomAdapter(response.body()));

                Toast.makeText(getContext(), "Found " + CustomAdapter.artistPojoList.artistList.size() + " Results.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArtistList> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        interfaceApi = retrofit.create(InterfaceApi.class);

    }
}
