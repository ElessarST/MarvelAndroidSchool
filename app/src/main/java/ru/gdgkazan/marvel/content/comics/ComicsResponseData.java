package ru.gdgkazan.marvel.content.comics;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by aydar on 17.09.16.
 */
public class ComicsResponseData extends RealmObject {

    @SerializedName("offset")
    private Long mOffset;

    @SerializedName("limit")
    private Long mLimit;

    @SerializedName("total")
    private Long mTotal;

    @SerializedName("count")
    private Long mCount;

    @SerializedName("results")
    private RealmList<Comics> mResults;

    public Long getOffset() {
        return mOffset;
    }

    public void setOffset(Long offset) {
        mOffset = offset;
    }

    public Long getLimit() {
        return mLimit;
    }

    public void setLimit(Long limit) {
        mLimit = limit;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public RealmList<Comics> getResults() {
        return mResults;
    }

    public void setResults(RealmList<Comics> results) {
        mResults = results;
    }
}
