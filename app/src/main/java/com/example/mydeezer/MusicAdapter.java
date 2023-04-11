package com.example.mydeezer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter {

    private ArrayList<Music> musics = new ArrayList<Music>();
    private Context context;

    public MusicAdapter(ArrayList<Music> musics, Context context) {
        this.musics = musics;
        this.context = context;
    }

    @Override
    public int getCount() {
        return musics.size();
    }

    @Override
    public Object getItem(int i) {
        return musics.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_music, viewGroup, false);
        }

        TextView textViewTitle = view.findViewById((R.id.textViewTitle));
        textViewTitle.setText(musics.get(i).getTitle());

        TextView textViewArtist = view.findViewById((R.id.textViewArtist));
        textViewArtist.setText(musics.get(i).getArtist());

        TextView textViewAlbum = view.findViewById((R.id.textViewAlbum));
        textViewAlbum.setText(musics.get(i).getAlbum());

        ImageView imageViewSong = view.findViewById(R.id.imageViewSong);
        ServicesApi.loadImage(context, musics.get(i).getCover(), imageViewSong);

        return view;
    }
}