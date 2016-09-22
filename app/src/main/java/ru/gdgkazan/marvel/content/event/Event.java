package ru.gdgkazan.marvel.content.event;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import ru.gdgkazan.marvel.content.Image;

/**
 * @author Olga Telezhnikova
 */
public class Event implements RealmModel {

    @PrimaryKey
    @SerializedName("id")
    private Long mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mDescription;

    /*
    * The canonical URL identifier for this resourcew
    */
    @SerializedName("resourceURI ")
    private String mResourceURI;

    /*
    * The date of publication of the first issue in this event
    */
    @SerializedName("start")
    private Date mStart;

     /*
    * The date of publication of the last issue in this event
    */
    @SerializedName("end")
    private Date mEnd;

    /*
    The representative image for this event
    */
    @SerializedName("thumbnail")
    private Image mImage;

    public Long getId() {
        return mId;
    }

    public void setId(Long mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getResourceURI() {
        return mResourceURI;
    }

    public void setResourceURI(String mResourceURI) {
        this.mResourceURI = mResourceURI;
    }

    public Date getStart() {
        return mStart;
    }

    public void setStart(Date mStart) {
        this.mStart = mStart;
    }

    public Image getImage() {
        return mImage;
    }

    public void setImage(Image mImage) {
        this.mImage = mImage;
    }

    public Date getEnd() {
        return mEnd;
    }

    public void setEnd(Date mEnd) {
        this.mEnd = mEnd;
    }
}
