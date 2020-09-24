package com.example.criteria_parser.utils;

/**
 * @author Lokesh chennamchetty
 * @date 25/09/2020
 */
public interface Constants {

    interface Keys {
        String TYPE = "type";
    }

    interface Regex {
        String DOLLAR_REGEX = "\\$[(0-9)]+";
    }

    interface Color {
        String GREEN = "green";
    }

    interface Type {
        String VARIABLE = "variable";
        String VALUE = "value";
        String INDICATOR = "indicator";
        String PLAIN_TEXT = "plain_text";
    }
}
