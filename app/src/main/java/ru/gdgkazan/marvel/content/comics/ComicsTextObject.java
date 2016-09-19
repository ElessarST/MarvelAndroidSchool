package ru.gdgkazan.marvel.content.comics;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by aydar on 17.09.16.
 */
public class ComicsTextObject extends RealmObject {

    @SerializedName("text")
    private String mText;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
