package com.example.criteria_parser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Lokesh chennamchetty
 * @date 25/09/2020
 */
public class BaseCriteria {

    @SerializedName("type")
    @Expose
    protected String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
