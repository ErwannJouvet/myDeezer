package com.example.mydeezer;

import java.io.Serializable;

public class Music implements Serializable {
    private String title;
    private String artist;
    private String album;
    private String cover;
    private String songUrl;
    private String link;

    public Music() {

    }
    public Music(String title, String artist, String album, String cover, String songUrl, String link) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.cover = cover;
        this.songUrl = songUrl;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}