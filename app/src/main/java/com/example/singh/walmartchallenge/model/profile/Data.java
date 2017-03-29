
package com.example.singh.walmartchallenge.model.profile;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("is_silhouette")
    @Expose
    private Boolean isSilhouette;
    @SerializedName("url")
    @Expose
    private String url;
    private final static long serialVersionUID = -1308992432600171740L;

    public Boolean getIsSilhouette() {
        return isSilhouette;
    }

    public void setIsSilhouette(Boolean isSilhouette) {
        this.isSilhouette = isSilhouette;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
