package ru.gdgkazan.marvel.content.event;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * @author Olga Telezhnikova
 */
public class EventsResponseData extends RealmObject {

    @SerializedName("offset")
    private Long mOffset;

    @SerializedName("limit")
    private Long mLimit;

    @SerializedName("total")
    private Long mTotal;

    @SerializedName("count")
    private Long mCount;

    @SerializedName("results")
    private RealmList<Event> mResults;

    public Long getmOffset() {
        return mOffset;
    }

    public void setOffset(Long mOffset) {
        this.mOffset = mOffset;
    }

    public RealmList<Event> getResults() {
        return mResults;
    }

    public void setResults(RealmList<Event> mResults) {
        this.mResults = mResults;
    }

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long mCount) {
        this.mCount = mCount;
    }

    public Long getLimit() {
        return mLimit;
    }

    public void setLimit(Long mLimit) {
        this.mLimit = mLimit;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long mTotal) {
        this.mTotal = mTotal;
    }
}
