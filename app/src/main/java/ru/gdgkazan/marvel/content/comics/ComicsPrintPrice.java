package ru.gdgkazan.marvel.content.comics;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by aydar on 17.09.16.
 */
public class ComicsPrintPrice extends RealmObject {

    @SerializedName("price")
    private float mPrice;

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }
}
