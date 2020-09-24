package com.example.criteria_parser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class CriteriaValues implements Serializable {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("values")
    @Expose
    private List<Double> values;
}
