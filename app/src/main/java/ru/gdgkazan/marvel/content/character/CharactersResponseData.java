
package ru.gdgkazan.marvel.content.character;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class CharactersResponseData extends RealmObject{

    @SerializedName("offset")
    private String offset;

    @SerializedName("limit")
    private String limit;

    @SerializedName("total")
    private String total;

    @SerializedName("count")
    private String count;

    @SerializedName("results")
    private RealmList<Character> results;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Character> getResults() {
        return results;
    }

    public void setResults(RealmList<Character> results) {
        this.results = results;
    }

}
