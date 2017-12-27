package edu.udacity.faraonc.tangent;

import android.support.annotation.NonNull;
import android.support.v7.content.res.AppCompatResources;

import java.io.Serializable;

/**
 * Created by ConardJames on 12/26/2017.
 */

class MusicImageBundle implements Serializable, Comparable {

    private int image;
    private String name;
    private MusicImageBundleEnum type;

    MusicImageBundle(MusicImageBundleEnum type, String name, int image) {
        this.type = type;
        this.name = name;
        this.image = image;
    }

    MusicImageBundleEnum getType() {
        return this.type;
    }

    String getName() {
        return this.name;
    }

    int getImage() {
        return this.image;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        MusicImageBundle rhs = (MusicImageBundle) o;
        return (this.name.compareTo(rhs.name));
    }

}
