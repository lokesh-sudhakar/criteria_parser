package com.example.criteria_parser.utils;

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



}
