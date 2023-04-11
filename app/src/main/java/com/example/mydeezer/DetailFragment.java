package com.example.mydeezer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

/* public class DetailFragment extends AppCompatActivity implements View.OnClickListener { */
public class DetailFragment extends Fragment implements View.OnClickListener {

    private TextView textViewTitle, textViewArtist, textViewAlbum;
    private ImageView imageViewAlbum;
    private Button buttonPLay, buttonLink;

    private Music music;

    private MediaPlayer player = new MediaPlayer();

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    /* @Override
    protected void onCreate(Bundle savedInstanceState) {

    public View onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

        textViewTitle = findViewById(R.id.TitleDetail);
        textViewArtist = findViewById(R.id.textViewArtistName);
        textViewAlbum = findViewById(R.id.textViewAlbumName);
        imageViewAlbum = findViewById(R.id.imageAlbumDetail);
        buttonPLay = findViewById(R.id.buttonPlayDetail);
        buttonLink = findViewById(R.id.buttonLinkDetail);

        buttonPLay.setOnClickListener(this);
        buttonLink.setOnClickListener(this);
    } */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detail, null);

        textViewTitle = v.findViewById(R.id.TitleDetail);
        textViewArtist = v.findViewById(R.id.textViewArtistName);
        textViewAlbum = v.findViewById(R.id.textViewAlbumName);
        imageViewAlbum = v.findViewById(R.id.imageAlbumDetail);
        buttonPLay = v.findViewById(R.id.buttonPlayDetail);
        buttonLink = v.findViewById(R.id.buttonLinkDetail);

        buttonPLay.setOnClickListener(this);
        buttonLink.setOnClickListener(this);

        return v;
    }

    public void refresh() {
        textViewTitle.setText(music.getTitle());
        textViewArtist.setText(music.getArtist());
        textViewAlbum.setText(music.getAlbum());
        ServicesApi.loadImage(getContext(), music.getCover(), imageViewAlbum);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(buttonLink)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(music.getLink()));
            startActivity(intent);
        } else if (!player.isPlaying()) {
            buttonPLay.setText("STOP");
            try {
                player.reset();
                player.setDataSource(getContext(), Uri.parse(music.getSongUrl()));
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            buttonPLay.setText("Play");
            player.stop();
        }
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        if(player.isPlaying()) {
            player.stop();
        }
    }*/
}