package com.example.criteria_parser.utils;

import com.example.criteria_parser.model.BaseCriteria;
import com.example.criteria_parser.model.Criteria;
import com.example.criteria_parser.model.CriteriaValues;
import com.example.criteria_parser.model.Indicator;
import com.example.criteria_parser.model.ScanData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lokesh chennamchetty
 * @date 25/09/2020
 */
public class BasicUtils {


    public static String toCamelCase(String value) {
        if (value == null) {
            return null;
        } else if (value.length() == 0) {
            return value;
        } else if (value.length()>1) {
            return  value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
        } else {
            return value.toUpperCase();
        }
    }

    public static String wrapParenthesis(String value){
        return "("+value+")";
    }

    public static String wrapParenthesis(int value){
        return "("+value+")";
    }


    public static List<ScanData> parseCriterias(List<ScanData> scanDataList) {
        Gson gson = new Gson();
        for (ScanData scanData : scanDataList) {
            for (Criteria criteria : scanData.getCriteria()) {
                String text = criteria.getText();
                String type = criteria.getType();
                Pattern pattern = Pattern.compile(Constants.Regex.DOLLAR_REGEX);
                Matcher matcher = pattern.matcher(text);
                List<String> patternKeys = new ArrayList<>();
                while (matcher.find()) {
                    patternKeys.add(matcher.group(0));
                }
                if (type.equalsIgnoreCase(Constants.Type.VARIABLE)) {
                    HashMap<String, BaseCriteria> parsedVariable = new HashMap<>();
                    for (String key : patternKeys) {
                        JsonObject keyObject = criteria.getVariable().get(key);
                        String keyType = keyObject.get(Constants.Keys.TYPE).getAsString();
                        if (keyType.equalsIgnoreCase(Constants.Type.VALUE)) {
                            CriteriaValues criteriaValues = gson.fromJson(keyObject.toString(),CriteriaValues.class);
                            parsedVariable.put(key,criteriaValues);
                        } else if (keyType.equalsIgnoreCase(Constants.Type.INDICATOR)) {
                            Indicator indicator = gson.fromJson(keyObject.toString(),Indicator.class);
                            parsedVariable.put(key,indicator);
                        }
                    }
                    criteria.setParsedVariable(parsedVariable);
                }
            }
        }
        return scanDataList;
    }
}
