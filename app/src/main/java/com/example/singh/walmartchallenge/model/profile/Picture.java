
package com.example.singh.walmartchallenge.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Picture implements Serializable
{

    @SerializedName("data")
    @Expose
    private Data data;
    private final static long serialVersionUID = -7853981255197778909L;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
