
package com.example.singh.walmartchallenge.model.friendList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Friendlist {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("paging")
    @Expose
    private Paging paging;
    @SerializedName("summary")
    @Expose
    private Summary summary;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

}
