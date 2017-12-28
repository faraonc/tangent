package edu.udacity.faraonc.tangent;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Music object for the mp3 player.
 *
 * @author ConardJames
 * @version 122817-01
 */
class Music implements Serializable, Comparable {

    //used if there is no album
    private final static String UNKNOWN = "Unknown";

    private int data;
    private int albumImage;
    private int artistImage;
    private String title;
    private String album;
    private String genre;
    private String artist;

    /**
     * Create a music object.
     *
     * @param data        the resource id of the raw audio.
     * @param title       the title of the music.
     * @param album       the album of the music.
     * @param genre       the genre of the music.
     * @param artist      the artist of the music.
     * @param albumImage  the resource id of the album image.
     * @param artistImage the resource id of the artist image.
     */
    Music(int data, String title, String album, String genre, String artist, int albumImage, int artistImage) {
        this.data = data;
        this.title = title;
        if (album.equalsIgnoreCase(UNKNOWN)) {
            this.album = artist;
        } else {
            this.album = album;
        }
        this.genre = genre;
        this.artist = artist;
        this.albumImage = albumImage;
        this.artistImage = artistImage;
    }

    /**
     * Return the resource id of the audio.
     *
     * @return the audio's resource id.
     */
    int getData() {
        return this.data;
    }

    /**
     * Set the audio data of the music.
     *
     * @param data the new resource id of the audio.
     */
    void setData(int data) {
        this.data = data;
    }

    /**
     * Get the audio title.
     *
     * @return the title of the music.
     */
    String getTitle() {
        return this.title;
    }

    /**
     * Set the title of the music.
     *
     * @param title the new title of the music
     */
    void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the music genre.
     *
     * @return the music genre.
     */
    String getGenre() {
        return this.genre;
    }

    /**
     * Set the music genre.
     *
     * @param genre the new music genre.
     */
    void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Get the artist's name.
     *
     * @return the artist's name.
     */
    String getArtist() {
        return this.artist;
    }

    /**
     * Set the artist's name.
     *
     * @param artist the new artist's name.
     */
    void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Get the album's name.
     *
     * @return the music album's name.
     */
    String getAlbum() {
        return this.album;
    }

    /**
     * Set the music album's name.
     *
     * @param album the new album's name.
     */
    void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Get the music album's image resource id.
     *
     * @return the album's image resource id.
     */
    int getAlbumImage() {
        return this.albumImage;
    }

    /**
     * Set the music album's image resource id.
     *
     * @param albumImage the new album's image resource id.
     */
    void setAlbumImage(int albumImage) {
        this.albumImage = albumImage;
    }

    /**
     * Get the music artist's image resource id.
     *
     * @return the artist's image resource id.
     */
    int getArtistImage() {
        return this.artistImage;
    }

    /**
     * Set the music artist's image resource id.
     *
     * @param artistImage the new artist's image resource id.
     */
    void setArtistImage(int artistImage) {
        this.artistImage = artistImage;
    }

    @Override
    /**
     * For Collections sorting.
     */
    public int compareTo(@NonNull Object o) {
        Music rhs = (Music) o;
        return (this.title.compareTo(rhs.title));
    }

    @Override
    /**
     * String representation of the Music
     */
    public String toString() {
        return this.title + " by " + this.artist + " - " + this.album;
    }
}
