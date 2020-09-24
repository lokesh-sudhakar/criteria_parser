package com.example.criteria_parser.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class Criteria {

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("text")
    private String text;

    @SerializedName("variable")
    private JsonObject variable;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public JsonObject getVariable() {
        return variable;
    }

    public void setVariable(JsonObject variable) {
        this.variable = variable;
    }

}
