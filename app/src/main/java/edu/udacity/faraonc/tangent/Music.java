package edu.udacity.faraonc.tangent;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by ConardJames on 12/25/2017.
 */

class Music implements Serializable, Comparable {

    private int data;
    private String title;
    private String album;
    private String genre;
    private String artist;

    public Music(int data, String title, String album, String genre, String artist) {
        this.data = data;
        this.title = title;
        this.album = album;
        this.genre = genre;
        this.artist = artist;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Music rhs = (Music)o;
        return (this.title.compareTo(rhs.title));
    }
}
