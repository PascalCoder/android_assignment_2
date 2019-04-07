package com.example.android_assignment_2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ArtistViewHolder>{

    public static ArtistList artistPojoList;
    public static String url;
    public static CardView cardView;
    public static int position;

    public CustomAdapter(ArtistList artistList){
        this.artistPojoList = artistList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ArtistViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder artistViewHolder, int i) {
        artistViewHolder.tvArtistName.setText(artistPojoList.artistList.get(i).getArtistName());
        artistViewHolder.tvCollectionName.setText(artistPojoList.artistList.get(i).getCollectionName());
        artistViewHolder.tvTrackPrice.setText(artistPojoList.artistList.get(i).getTrackPrice() + " USD");

        Picasso.get().load(artistPojoList.artistList.get(i).getArtWorkUrl60())
                .resize(200, 200)
                .into(artistViewHolder.imageView);

        url = artistPojoList.artistList.get(i).getPreviewUrl();
        position = i;
    }

    @Override
    public int getItemCount() {
        return artistPojoList.artistList.size();
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvArtistName;
        TextView tvCollectionName;
        TextView tvTrackPrice;

        CardView cardView;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_item);
            tvArtistName = itemView.findViewById(R.id.tv_artist_name);
            tvCollectionName = itemView.findViewById(R.id.tv_collection);
            tvTrackPrice = itemView.findViewById(R.id.tv_price);

            Log.d("isClassicDisplayed", "" + MainActivity.isClassicFragmentDisplayed);
            cardView = itemView.findViewById(R.id.cv_item);
            final String url = CustomAdapter.url;

            if(MainActivity.isClassicFragmentDisplayed) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), url, Toast.LENGTH_SHORT).show();

                        playMusic(url);
                    }
                });

            }
        }

        public void playMusic(String aUrl){

            final MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            try{
                mediaPlayer.setDataSource(aUrl);
                mediaPlayer.prepare();
                mediaPlayer.start();
            }catch(IOException e){
                e.printStackTrace();
            }

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                    //mediaPlayer = null;
                }
            });

        }
    }
}
