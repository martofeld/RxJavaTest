package com.mfeldsztejn.rxjavatest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mfeldsztejn on 9/5/16.
 */

public class Item implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }
}
