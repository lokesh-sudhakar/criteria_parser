package com.example.criteria_parser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class Indicator extends BaseCriteria implements Serializable {

    @SerializedName("study_type")
    @Expose
    private String studyType;

    @SerializedName("parameter_name")
    @Expose
    private String parameterName;

    @SerializedName("min_value")
    @Expose
    private int minValue;

    @SerializedName("max_value")
    @Expose
    private int maxValue;

    @SerializedName("default_value")
    @Expose
    private int defaultValue;

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }
}
