package com.example.criteria_parser.utils;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.example.criteria_parser.listeners.CriteriaItemListener;
import com.example.criteria_parser.model.BaseCriteria;
import com.example.criteria_parser.model.Criteria;
import com.example.criteria_parser.model.CriteriaValues;
import com.example.criteria_parser.model.Indicator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lokesh chennamchetty
 * @date 25/09/2020
 */
public class SpanUtils {

    public static SpannableStringBuilder getCriteriaSpannableString(Criteria criteria, CriteriaItemListener listener) {
        Matcher matcher = Pattern.compile(Constants.Regex.DOLLAR_REGEX).matcher(criteria.getText());
        SpannableStringBuilder spannableString = new SpannableStringBuilder(criteria.getText());
        for (Map.Entry<String, BaseCriteria> entry : criteria.getParsedVariable().entrySet()) {
            String replaceableString = "";
            ClickableSpan clickableSpan = null;
            if (entry.getValue() instanceof CriteriaValues) {
                CriteriaValues criteriaValues = (CriteriaValues) entry.getValue();
                replaceableString = BasicUtils.wrapParenthesis(criteriaValues.getValues().get(0).toString());
                clickableSpan = getValueClickableSpan(criteriaValues, listener);
            } else if (entry.getValue() instanceof Indicator) {
                Indicator indicator = (Indicator) entry.getValue();
                replaceableString = BasicUtils.wrapParenthesis(indicator.getDefaultValue());
                clickableSpan = getIndicatorClickableSpan(indicator, listener);
            }
            int start = criteria.getText().indexOf(entry.getKey());
            int end = start+entry.getKey().length();
            spannableString.replace(start, end, replaceableString, 0,
                    replaceableString.length());
            spannableString.setSpan(clickableSpan, start,
                    start + replaceableString.length(),
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
