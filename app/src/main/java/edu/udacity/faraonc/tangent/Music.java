package edu.udacity.faraonc.tangent;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by ConardJames on 12/25/2017.
 */

class Music implements Serializable, Comparable {

    private int data;
    private int albumImage;
    private int artistImage;
    private String title;
    private String album;
    private String genre;
    private String artist;

    Music(int data, String title, String album, String genre, String artist, int albumImage, int artistImage) {
        this.data = data;
        this.title = title;
        this.album = album;
        this.genre = genre;
        this.artist = artist;
        this.albumImage = albumImage;
        this.artistImage = artistImage;
    }

    int getData() {
        return this.data;
    }

    void setData(int data) {
        this.data = data;
    }

    String getTitle() {
        return this.title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getGenre() {
        return genre;
    }

    void setGenre(String genre) {
        this.genre = genre;
    }

    String getArtist() {
        return artist;
    }

    void setArtist(String artist) {
        this.artist = artist;
    }

    String getAlbum() {
        return this.album;
    }

    void setAlbum(String album) {
        this.album = album;
    }

    int getAlbumImage() {
        return this.albumImage;
    }

    void setAlbumImage(int albumImage) {
        this.albumImage = albumImage;
    }

    int getArtistImage() {
        return this.artistImage;
    }

    void setArtistImage(int artistImage) {
        this.artistImage = artistImage;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Music rhs = (Music) o;
        return (this.title.compareTo(rhs.title));
    }
}
