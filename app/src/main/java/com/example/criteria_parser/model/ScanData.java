package com.example.criteria_parser.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class ScanData implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("tag")
    private String tag;

    @SerializedName("color")
    private String color;

    @SerializedName("criteria")
    private List<Criteria> criteria;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Criteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }

}