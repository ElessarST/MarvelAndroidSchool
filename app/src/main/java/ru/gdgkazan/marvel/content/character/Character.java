
package ru.gdgkazan.marvel.content.character;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.gdgkazan.marvel.content.Image;
import ru.gdgkazan.marvel.content.MarvelDataList;

public class Character extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private Image image;

    @SerializedName("comics")
    private MarvelDataList comics;

    @SerializedName("stories")
    private MarvelDataList stories;

    @SerializedName("events")
    private MarvelDataList events;

    @SerializedName("series")
    private MarvelDataList series;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public MarvelDataList getComics() {
        return comics;
    }

    public void setComics(MarvelDataList comics) {
        this.comics = comics;
    }

    public MarvelDataList getStories() {
        return stories;
    }

    public void setStories(MarvelDataList stories) {
        this.stories = stories;
    }

    public MarvelDataList getEvents() {
        return events;
    }

    public void setEvents(MarvelDataList events) {
        this.events = events;
    }

    public MarvelDataList getSeries() {
        return series;
    }

    public void setSeries(MarvelDataList series) {
        this.series = series;
    }
}
