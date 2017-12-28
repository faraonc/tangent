package edu.udacity.faraonc.tangent;

import android.util.Log;

import java.io.Serializable;
import java.util.Collections;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;

/**
 * Manages the musics.
 *
 * @author ConardJames
 * @version 122817-01
 */
class MusicManager implements Serializable {

    private TreeMap<String, ArrayList<Music>> genresMap;
    private TreeMap<String, ArrayList<Music>> artistsMap;
    private TreeMap<String, ArrayList<Music>> albumsMap;
    private TreeMap<String, MusicImageBundle> artistsImageMap;
    private TreeMap<String, MusicImageBundle> albumsImageMap;
    private TreeSet<Music> songsSet;

    /**
     * Construct an object that maintains music data structures.
     */
    MusicManager() {
        this.genresMap = new TreeMap<>();
        this.artistsMap = new TreeMap<>();
        this.albumsMap = new TreeMap<>();
        this.artistsImageMap = new TreeMap<>();
        this.albumsImageMap = new TreeMap<>();
        this.songsSet = new TreeSet<>();
    }

    /**
     * Get all the songs.
     *
     * @return all the musics.
     */
    TreeSet<Music> getSongsList() {
        return this.songsSet;
    }

    /**
     * Get the songs of an artist.
     *
     * @param artistKey the key for retrieving the songs.
     * @return the artist's songs.
     */
    ArrayList<Music> getArtistSongsList(String artistKey) {
        if (this.artistsMap.containsKey(artistKey)) {
            return this.artistsMap.get(artistKey);
        }
        return null;
    }

    /**
     * Get the songs of a genre.
     *
     * @param genreKey the key for retrieving the songs.
     * @return the genre's songs.
     */
    ArrayList<Music> getGenreSongsList(String genreKey) {
        if (this.genresMap.containsKey(genreKey)) {
            return this.genresMap.get(genreKey);
        }
        return null;
    }

    /**
     * Get the songs of an album.
     *
     * @param albumKey the key for retrieving the songs.
     * @return the album's songs.
     */
    ArrayList<Music> getAlbumSongsList(String albumKey) {
        if (this.albumsMap.containsKey(albumKey)) {
            return this.albumsMap.get(albumKey);
        }
        return null;
    }

    /**
     * Get all the genres.
     *
     * @return the all the genres.
     */
    Set<String> getGenres() {
        return this.genresMap.keySet();
    }

    /**
     * Add music to the data structures.
     *
     * @param music the Music to be added.
     * @return true if successfully added.
     */
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

            //store images for the artists and the albums
            if (!this.artistsImageMap.containsKey(artist)) {
                this.artistsImageMap.put(artist, new MusicImageBundle(MusicImageBundle.ImageBundleEnum.ARTISTS, music.getArtist(), music.getArtistImage()));
            }

            if (!this.albumsImageMap.containsKey(album)) {
                this.albumsImageMap.put(artist, new MusicImageBundle(MusicImageBundle.ImageBundleEnum.ALBUMS, music.getAlbum(), music.getAlbumImage()));
            }

            return true;
        }
        return false;
    }

    /**
     * Get the image mapping of artists.
     *
     * @return the image map of artists.
     */
    TreeMap<String, MusicImageBundle> getArtistsImageMap() {
        return this.artistsImageMap;
    }

    /**
     * Get the image mapping of albums.
     *
     * @return the image map of albums.
     */
    TreeMap<String, MusicImageBundle> getAlbumsImageMap() {
        return this.albumsImageMap;
    }

    /**
     * Print all the songs.
     */
    void printSongsSet() {
        for (Music item : this.songsSet) {
            Log.v("songsSet", item.getTitle());
        }
    }

    /**
     * Print the genres and the songs for each genre.
     */
    void printGenresMap() {
        for (String key : this.genresMap.keySet()) {
            ArrayList<Music> value = this.genresMap.get(key);
            Collections.sort(value);
            for (Music item : value)
                Log.v("genresMap", key + " -> " + item.getTitle());
        }
    }

    /**
     * Print the artists and the songs for each artist.
     */
    void printArtistsMap() {
        for (String key : this.artistsMap.keySet()) {
            ArrayList<Music> value = this.artistsMap.get(key);
            Collections.sort(value);
            for (Music item : value)
                Log.v("artistsMap", key + " -> " + item.getTitle());
        }
    }

    /**
     * Print the albums and the songs for each album.
     */
    void printAlbumsMap() {
        for (String key : this.albumsMap.keySet()) {
            ArrayList<Music> value = this.albumsMap.get(key);
            Collections.sort(value);
            for (Music item : value)
                Log.v("albumsMap", key + " -> " + item.getTitle());
        }
    }

    /**
     * Prints the album and image bundle string representation.
     */
    void printAlbumsImageMap() {
        for (String key : this.albumsImageMap.keySet()) {
            MusicImageBundle value = this.albumsImageMap.get(key);
            Log.v("albumsImageMap", key + " -> " + value);
        }
    }

    /**
     * Prints the artist and image bundle string representation.
     */
    void printArtistsImageMap() {
        for (String key : this.artistsImageMap.keySet()) {
            MusicImageBundle value = this.artistsImageMap.get(key);
            Log.v("artistsImageMap", key + " -> " + value);
        }
    }

    //TODO removeMusic
    //This is not needed for the project requirement.
}
