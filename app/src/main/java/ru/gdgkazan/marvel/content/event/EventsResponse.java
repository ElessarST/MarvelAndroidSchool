package ru.gdgkazan.marvel.content.event;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * @author Olga Telezhnikova
 */
public class EventsResponse extends RealmObject {

    @SerializedName("data")
    private EventsResponseData mData;

    public EventsResponseData getData() {
        return mData;
    }

    public void setData(EventsResponseData data) {
        mData = data;
    }
}
