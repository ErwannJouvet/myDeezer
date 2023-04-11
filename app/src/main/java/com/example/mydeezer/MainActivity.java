package com.example.mydeezer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IOnSelectedMusic {

    private SearchFragment searchFragment;
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFragment = new SearchFragment();
        detailFragment = new DetailFragment();

        getSupportFragmentManager().beginTransaction()
            .add(R.id.frameLayout, searchFragment)
            .add(R.id.frameLayout, detailFragment)
            .hide(detailFragment)
            .commit();

        searchFragment.setListener(this);
    }

    @Override
    public void selectedMusic(Music m) {
        detailFragment.setMusic(m);
        if (isMobile()){
        getSupportFragmentManager().beginTransaction()
                .hide(searchFragment)
                .show(detailFragment)
                .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                .show(detailFragment)
                .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (detailFragment.isVisible()) {
            if (isMobile()) {
                getSupportFragmentManager().beginTransaction()
                        .hide(detailFragment)
                        .show(searchFragment)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                    .show(searchFragment)
                    .commit();
            }
        }
        else{
            super.onBackPressed();
        }
    }

    private boolean isMobile(){
        return findViewById(R.id.frameLayout) != null;
    }
}
