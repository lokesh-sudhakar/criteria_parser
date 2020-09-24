package com.example.criteria_parser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class CriteriaValues extends BaseCriteria implements Serializable {

    @SerializedName("values")
    @Expose
    private List<Double> values;

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
}
