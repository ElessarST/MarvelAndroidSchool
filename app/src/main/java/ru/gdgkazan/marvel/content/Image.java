package ru.gdgkazan.marvel.content;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by aydar on 17.09.16.
 */
public class Image extends RealmObject implements Serializable{

    @SerializedName("path")
    private String mPath;

    @SerializedName("extension")
    private String mExtension;

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public String getExtension() {
        return mExtension;
    }

    public void setExtension(String extension) {
        mExtension = extension;
    }
}
