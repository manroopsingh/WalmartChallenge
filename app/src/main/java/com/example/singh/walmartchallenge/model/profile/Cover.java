
package com.example.singh.walmartchallenge.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cover implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("offset_y")
    @Expose
    private Integer offsetY;
    @SerializedName("source")
    @Expose
    private String source;
    private final static long serialVersionUID = 504521437777461683L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(Integer offsetY) {
        this.offsetY = offsetY;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
