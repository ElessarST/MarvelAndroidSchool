
package ru.gdgkazan.marvel.content.character;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class CharactersResponse extends RealmObject {

    @SerializedName("data")
    private CharactersResponseData data;

    public CharactersResponseData getData() {
        return data;
    }

    public void setData(CharactersResponseData data) {
        this.data = data;
    }
}
