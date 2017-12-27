package edu.udacity.faraonc.tangent;

import android.util.Log;

import java.io.Serializable;
import java.util.Collections;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;

/**
 * Created by ConardJames on 12/25/2017.
 */

class MusicManager implements Serializable {

    private TreeMap<String, ArrayList<Music>> genresMap;
    private TreeMap<String, ArrayList<Music>> artistsMap;
    private TreeMap<String, ArrayList<Music>> albumsMap;
    private TreeMap<String, ArtistImageBundle> artistsImageMap;
    private TreeMap<String, AlbumImageBundle> albumsImageMap; //TODO implement image mapping
    private TreeSet<Music> songsSet;

    MusicManager() {
        this.genresMap = new TreeMap<>();
        this.artistsMap = new TreeMap<>();
        this.albumsMap = new TreeMap<>();
        this.artistsImageMap = new TreeMap<>();
        this.albumsImageMap = new TreeMap<>();
        this.songsSet = new TreeSet<>();
    }

    class ArtistImageBundle{
        private int image;
        private String artist;

        ArtistImageBundle(int image, String artist){
            this.image = image;
            this.artist = artist;
        }

        int getArtistImage(){
           return this.image;
        }

        String getArtistName(){
            return this.artist;
        }

    }

    class AlbumImageBundle{

        private int image;
        private String album;

        AlbumImageBundle(int image, String album){
            this.image = image;
            this.album = album;
        }

        int getArtistImage(){
            return this.image;
        }

        String getAlbumName(){
            return this.album;
        }

    }

    TreeSet<Music> getSongsList() {
        return this.songsSet;
    }

    ArrayList<Music> getArtistSongsList(String artistKey) {
        if (this.artistsMap.containsKey(artistKey)) {
            return this.artistsMap.get(artistKey);
        }
        return null;
    }

    ArrayList<Music> getGenreSongsList(String genreKey) {
        if (this.genresMap.containsKey(genreKey)) {
            return this.genresMap.get(genreKey);
        }
        return null;
    }

    ArrayList<Music> getAlbumSongsList(String albumKey) {
        if (this.albumsMap.containsKey(albumKey)) {
            return this.albumsMap.get(albumKey);
        }
        return null;
    }

    Set<String> getArtists() {
        return this.artistsMap.keySet();
    }

    Set<String> getGenres() {
        return this.genresMap.keySet();
    }

    boolean addMusic(Music music) {
        if (this.songsSet.add(music)) {

            String genre = music.getGenre();
            if (!this.genresMap.containsKey(genre)) {
                this.genresMap.put(genre, new ArrayList<Music>());
            }
            this.genresMap.get(genre).add(music);

            String artist = music.getArtist();
            if (!this.artistsMap.containsKey(artist)) {
                this.artistsMap.put(artist, new ArrayList<Music>());
            }
            this.artistsMap.get(artist).add(music);

            String album = music.getAlbum();
            if (!this.albumsMap.containsKey(album)) {
                this.albumsMap.put(album, new ArrayList<Music>());
            }
            this.albumsMap.get(album).add(music);

            if(!this.artistsImageMap.containsKey(artist)){
                this.artistsImageMap.put(artist, new ArtistImageBundle(music.getArtistImage(), music.getArtist()));
            }

            if(!this.albumsImageMap.containsKey(album)){
                this.albumsImageMap.put(artist, new AlbumImageBundle(music.getAlbumImage(), music.getAlbum()));
            }

            return true;
        }
        return false;
    }

    //TODO removeMusic
    //This is not needed for the project requirement.

    void printSongsSet() {
        for (Music item : this.songsSet) {
            Log.v("songsSet", item.getTitle());
        }
    }

    void printGenresMap() {
        for (String key : this.genresMap.keySet()) {
            ArrayList<Music> value = this.genresMap.get(key);
            Collections.sort(value);
            for (Music item : value)
                Log.v("genresMap", key + " -> " + item.getTitle());
        }
    }

    void printArtistsMap() {
        for (String key : this.artistsMap.keySet()) {
            ArrayList<Music> value = this.artistsMap.get(key);
            Collections.sort(value);
            for (Music item : value)
                Log.v("artistsMap", key + " -> " + item.getTitle());
        }
    }

    void printAlbumsMap() {
        for (String key : this.albumsMap.keySet()) {
            ArrayList<Music> value = this.albumsMap.get(key);
            Collections.sort(value);
            for (Music item : value)
                Log.v("albumsMap", key + " -> " + item.getTitle());
        }
    }

}
