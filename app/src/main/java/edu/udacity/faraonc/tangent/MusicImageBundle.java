package edu.udacity.faraonc.tangent;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Package of images and names for music.
 * Used for albums, artists and their respective images.
 *
 * @author ConardJames
 * @version 122817-01
 */
class MusicImageBundle implements Serializable, Comparable {

    enum ImageBundleEnum {
        ARTISTS,
        ALBUMS,
    }

    private int image;
    private String name;
    private ImageBundleEnum type;

    /**
     * Package an image and name related to music.
     *
     * @param type  the package type.
     * @param name  the name associated with the package.
     * @param image the image resource id associated with the package.
     */
    MusicImageBundle(ImageBundleEnum type, String name, int image) {
        this.type = type;
        this.name = name;
        this.image = image;
    }

    /**
     * Get the type of package.
     *
     * @return the music image bundle type.
     */
    ImageBundleEnum getType() {
        return this.type;
    }

    /**
     * Get the name of album or artist associated with the image.
     *
     * @return the name of album or artist.
     */
    String getName() {
        return this.name;
    }

    /**
     * Get the image resource id associated with the package.
     *
     * @return the image resource id associated with the package.
     */
    int getImage() {
        return this.image;
    }

    @Override
    /**
     * For Collections sorting.
     */
    public int compareTo(@NonNull Object o) {
        MusicImageBundle rhs = (MusicImageBundle) o;
        return (this.name.compareTo(rhs.name));
    }

    @Override
    /**
     * String representation of the bundle.
     */
    public String toString() {
        return this.type + ": " + this.name;
    }
}
