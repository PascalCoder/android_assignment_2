package com.example.android_assignment_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //RockFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RockFragment extends Fragment {

    RecyclerView recyclerView;
    List<ArtistPojo> list;
    InterfaceApi interfaceApi;

    public RockFragment() {
        // Required empty public constructor
    }

    public static RockFragment newInstance(){
        return new RockFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_rock, container, false);

        initializeRetrofit();

        recyclerView = rootView.findViewById(R.id.rock_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        getArtists();
        //initializeRetrofit();

        return rootView;
    }

    private void getArtists() {
        interfaceApi.getRockArtists().enqueue(new Callback<ArtistList>() {
            @Override
            public void onResponse(Call<ArtistList> call, Response<ArtistList> response) {
                Log.d("Message", "Inside onResponse before setting adapter");
                recyclerView.setAdapter(new CustomAdapter(response.body()));
                Log.d("Message", "Inside onResponse after setting adapter");
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
        //makeRetrofitCall(interfaceApi);
    }

    /*private void makeRetrofitCall(InterfaceApi api) {

        //if(isRockFragmentDisplayed){
            api.getRockArtists().enqueue(new Callback<ArtistList>() {
                @Override
                public void onResponse(Call<ArtistList> call, Response<ArtistList> response) {
                    if(response.body() != null){
                        //add the result to the view
                        //(new MainActivity()).recyclerView.setAdapter(new CustomAdapter(response.body()));
                        recyclerView.setAdapter(new CustomAdapter(response.body()));
                    }else{
                        Log.d("Message", "response.body() == null");
                        recyclerView.setAdapter(new CustomAdapter(response.body()));
                    }
                }

                @Override
                public void onFailure(Call<ArtistList> call, Throwable t) {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        //}
    }*/
}
