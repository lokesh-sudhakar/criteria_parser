package com.example.criteria_parser.utils;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

import com.example.criteria_parser.listeners.CriteriaItemListener;
import com.example.criteria_parser.model.Criteria;
import com.example.criteria_parser.model.CriteriaValues;
import com.example.criteria_parser.model.Indicator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lokesh chennamchetty
 * @date 25/09/2020
 */
public class SpanUtils {

    private static final String TAG = "criteria";

    public static SpannableStringBuilder getCriteriaSpannableString(Criteria criteria, CriteriaItemListener listener) {
        Gson gson = new Gson();
        Matcher matcher = Pattern.compile(Constants.Regex.DOLLAR_REGEX).matcher(criteria.getText());
        SpannableStringBuilder spannableString = new SpannableStringBuilder(criteria.getText());
        while (matcher.find()) {
            Log.d(TAG, "regex keys: " + matcher.group(0));
            String key = matcher.group(0);
            JsonObject keyJsonObject = criteria.getVariable().getAsJsonObject(key);
            String keyType = keyJsonObject.get(Constants.Keys.TYPE).getAsString();
            String replaceableString = "";
            ClickableSpan clickableSpan = null;
            if (keyType.equalsIgnoreCase(Constants.Type.VALUE)) {
                CriteriaValues criteriaValues = gson.fromJson(keyJsonObject.toString(), CriteriaValues.class);
                replaceableString = BasicUtils.wrapParenthesis(criteriaValues.getValues().get(0).toString());
                clickableSpan = getValueClickableSpan(criteriaValues, listener);
            } else if (keyType.equalsIgnoreCase(Constants.Type.INDICATOR)) {
                Indicator indicator = gson.fromJson(keyJsonObject.toString(), Indicator.class);
                replaceableString = BasicUtils.wrapParenthesis(indicator.getDefaultValue());

                clickableSpan = getIndicatorClickableSpan(indicator, listener);
            }
            spannableString.replace(matcher.start(), matcher.end(), replaceableString, 0,
                    replaceableString.length());
            spannableString.setSpan(clickableSpan, matcher.start(),
                    matcher.start() + replaceableString.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    private static ClickableSpan getValueClickableSpan(CriteriaValues criteriaValues, CriteriaItemListener listener) {
        return new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                listener.onCriteriaValueClick(criteriaValues);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
    }

    private static ClickableSpan getIndicatorClickableSpan(Indicator indicator, CriteriaItemListener listener) {
        return new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                listener.onCriteriaIndicatorClick(indicator);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
    }
}
