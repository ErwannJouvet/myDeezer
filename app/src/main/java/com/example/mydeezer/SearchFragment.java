package com.example.mydeezer;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener, IListenerApi, View.OnClickListener {

    private ArrayList<Music> musics = new ArrayList<Music>();
    private ListView listViewSearch;
    private EditText editTextSearch;
    private ImageButton imageButtonSearch;

    private IOnSelectedMusic listener;

    public void setListener(IOnSelectedMusic listener) {
        this.listener = listener;
    }

    /* @Override
    public View onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);
        musics.add(new Music("MEGATRON", "Laylow", "TRINITY", "https://api.deezer.com/album/132530842/image", "https://cdns-preview-2.dzcdn.net/stream/c-29a57d01c969480aa303eb398c1c7d26-5.mp3", "https://www.deezer.com/fr/track/882365782"));

        listViewSearch = findViewById(R.id.listViewSearch);
        MusicAdapter musicAdapter = new MusicAdapter(musics, this);
        listViewSearch.setAdapter(musicAdapter);

        editTextSearch = findViewById(R.id.editTextSearch);
        imageButtonSearch = findViewById(R.id.imageButtonSearch);
        imageButtonSearch.setOnClickListener(this);

        listViewSearch.setOnItemClickListener(this);
    } */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_search, null);

        listViewSearch = v.findViewById(R.id.listViewSearch);
        MusicAdapter musicAdapter = new MusicAdapter(musics, getContext());
        listViewSearch.setAdapter(musicAdapter);

        editTextSearch = v.findViewById(R.id.editTextSearch);
        imageButtonSearch = v.findViewById(R.id.imageButtonSearch);
        imageButtonSearch.setOnClickListener(this);

        listViewSearch.setOnItemClickListener(this);

        return v;
    }

    /*@Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent myIntent = new Intent(SearchFragment.this, DetailFragment.class);
        myIntent.putExtra("selectedMusic", musics.get(i));
        startActivity(myIntent);
    }*/

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        listener.selectedMusic(musics.get(i));
    }

    @Override
    public void onReceiveMusics(ArrayList<Music> musics) {
        this.musics = musics;
        MusicAdapter adapter = new MusicAdapter(musics, getContext());
        listViewSearch.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (editTextSearch.getText().toString() != "") {
            ServicesApi.musicsRequest(getContext(), editTextSearch.getText().toString(), this);
        }
    }


}