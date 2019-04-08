package com.example.android_assignment_2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
    public static String currentUrl;
    public static String newUrl;
    public static CardView cardView;
    public static MediaPlayer mediaPlayer;

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
        artistViewHolder.tvUrl.setText(artistPojoList.artistList.get(i).getPreviewUrl());

        Picasso.get().load(artistPojoList.artistList.get(i).getArtWorkUrl60())
                .resize(200, 200)
                .into(artistViewHolder.imageView);

        if(currentUrl == null || currentUrl.equals("") || currentUrl.length()==0) {
            currentUrl = artistPojoList.artistList.get(i).getPreviewUrl(); //playing url
        }else{
            newUrl = artistPojoList.artistList.get(i).getPreviewUrl();
        }
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
        TextView tvUrl;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_item);
            tvArtistName = itemView.findViewById(R.id.tv_artist_name);
            tvCollectionName = itemView.findViewById(R.id.tv_collection);
            tvTrackPrice = itemView.findViewById(R.id.tv_price);
            tvUrl = itemView.findViewById(R.id.tv_url);

            cardView = itemView.findViewById(R.id.cv_item);

            if(MainActivity.isClassicFragmentDisplayed) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(v.getContext(), tvArtistName.getText().toString(), Toast.LENGTH_SHORT).show();

                        if(isMusicPlaying(currentUrl)){
                            stopMusic(mediaPlayer);
                            playMusic(tvUrl.getText().toString());
                        }else if(isMusicPlaying(tvUrl.getText().toString())){
                            stopMusic(mediaPlayer);
                        }
                        else{
                            if(mediaPlayer != null)
                                stopMusic(mediaPlayer);
                            playMusic(tvUrl.getText().toString());
                        }

                    }
                });

            }
        }

        public void playMusic(String url){
            final MediaPlayer mediaPlayer = new MediaPlayer();
            CustomAdapter.mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            CustomAdapter.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            try{
                CustomAdapter.mediaPlayer.setDataSource(url);
                CustomAdapter.mediaPlayer.prepare();
                CustomAdapter.mediaPlayer.start();
            }catch(IOException e){
                e.printStackTrace();
            }

            CustomAdapter.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    CustomAdapter.mediaPlayer.release();
                    CustomAdapter.mediaPlayer = null;
                }
            });

        }

        public void stopMusic(MediaPlayer mediaPlayer){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        public boolean isMusicPlaying(String url){
            MediaPlayer mediaPlayer = new MediaPlayer();

            try{
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
            }catch (IOException e){
                e.printStackTrace();
            }

            return mediaPlayer.isPlaying();
        }
    }
}
