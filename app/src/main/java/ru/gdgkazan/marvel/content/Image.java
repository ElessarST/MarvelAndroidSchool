package ru.gdgkazan.marvel.content;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by aydar on 17.09.16.
 */
public class Image extends RealmObject {

    @SerializedName("path")
    private String mPath;

    @SerializedName("extension")
    private String mExtension;
}
