package com.example.criteria_parser.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

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
    private Map<String, JsonObject> variable;

    private Map<String, BaseCriteria> parsedVariable;


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

    public Map<String, JsonObject> getVariable() {
        return variable;
    }

    public void setVariable(Map<String, JsonObject> variable) {
        this.variable = variable;
    }

    public Map<String, BaseCriteria> getParsedVariable() {
        return parsedVariable;
    }

    public void setParsedVariable(Map<String, BaseCriteria> parsedVariable) {
        this.parsedVariable = parsedVariable;
    }
}
