package ru.gdgkazan.marvel.content.comics;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.gdgkazan.marvel.content.Image;
import ru.gdgkazan.marvel.screen.common.ListItem;

/**
 * Created by aydar on 17.09.16.
 */
public class Comics extends RealmObject implements ListItem {

    @PrimaryKey
    @SerializedName("id")
    private Long mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("pageCount")
    private int mPageCount;

    @SerializedName("textObjects")
    private RealmList<ComicsTextObject> mTextObjects;

    @SerializedName("prices")
    private RealmList<ComicsPrintPrice> mPrices;

    @SerializedName("images")
    private RealmList<Image> mImages;

    public Comics() {
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getPageCount() {
        return mPageCount;
    }

    public void setPageCount(int pageCount) {
        mPageCount = pageCount;
    }

    public RealmList<ComicsTextObject> getTextObjects() {
        return mTextObjects;
    }

    public void setTextObjects(RealmList<ComicsTextObject> textObjects) {
        mTextObjects = textObjects;
    }

    public RealmList<ComicsPrintPrice> getPrices() {
        return mPrices;
    }

    public void setPrices(RealmList<ComicsPrintPrice> prices) {
        mPrices = prices;
    }

    public RealmList<Image> getImages() {
        return mImages;
    }

    public void setImages(RealmList<Image> images) {
        mImages = images;
    }

    @Override
    public String getName() {
        return getTitle();
    }

    @Override
    public String getDescription() {
        final int consoleWidth = 80;
        return getTextObjects().get(0).getText().substring(consoleWidth);
    }

    @Override
    public Image getImage() {
        return mImages.get(0);
    }
}
