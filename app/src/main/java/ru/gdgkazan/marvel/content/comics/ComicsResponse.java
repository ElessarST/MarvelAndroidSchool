package ru.gdgkazan.marvel.content.comics;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by aydar on 17.09.16.
 */
public class ComicsResponse extends RealmObject {

    @SerializedName("data")
    private ComicsResponseData mData;

    public ComicsResponseData getData() {
        return mData;
    }

    public void setData(ComicsResponseData data) {
        mData = data;
    }
}
