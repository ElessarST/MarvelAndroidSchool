package ru.gdgkazan.marvel.content;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class MarvelDataList extends RealmObject {

    @SerializedName("available")
    private String available;
    @SerializedName("returned")
    private String returned;
    @SerializedName("collectionURI")
    private String collectionURI;

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }
}
