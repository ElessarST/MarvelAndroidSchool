package ru.gdgkazan.marvel.content.character;


import com.google.gson.annotations.SerializedName;

public class SingleCharacterResponse {

    @SerializedName("characterById")
    private Character character;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
