package com.example.criteria_parser.adapter;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criteria_parser.databinding.CriteriaListItemBinding;
import com.example.criteria_parser.listeners.CriteriaItemListener;
import com.example.criteria_parser.model.Criteria;
import com.example.criteria_parser.model.CriteriaValues;
import com.example.criteria_parser.model.Indicator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class CriteriaListViewHolder  extends RecyclerView.ViewHolder {

    private static final String TAG = "criteria";
    private Gson gson;
    private CriteriaListItemBinding binding;


    public CriteriaListViewHolder(@NonNull CriteriaListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        this.gson = new Gson();
    }

    public void bind(Criteria criteria,boolean isLastItem, CriteriaItemListener listener){
        if (isLastItem) {
            binding.andDelimeter.setVisibility(View.GONE);
        }
        binding.name.setText(criteria.getText());
        String text = criteria.getText();
        if (criteria.getType().equalsIgnoreCase("plain_text")) {
            binding.name.setText(criteria.getText());
        } else if (criteria.getType().equalsIgnoreCase("variable")) {
            SpannableStringBuilder spannableString = getSpannableString(criteria, listener);

            binding.name.setText(spannableString);
            binding.name.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private SpannableStringBuilder getSpannableString(Criteria criteria, CriteriaItemListener listener) {
        Pattern pattern = Pattern.compile("\\$[(0-9)]+");
        Matcher matcher = pattern.matcher(criteria.getText());
        List<String> patternKeys = new ArrayList<>();
        SpannableStringBuilder spannableString = new SpannableStringBuilder(criteria.getText());
        while (matcher.find()) {
            Log.d(TAG, "regex keys: " + matcher.group(0));
            patternKeys.add(matcher.group(0));
            String key  = matcher.group(0);

            ClickableSpan clickableSpan = getClickableSpan(criteria, listener, key);

            JsonObject keyJsonObject = criteria.getVariable().getAsJsonObject(key);
            String keyType = keyJsonObject.get("type").getAsString();
            String replacableString = "";
            if (keyType.equalsIgnoreCase("value")) {
                CriteriaValues criteriaValues = gson.fromJson(keyJsonObject.toString(),CriteriaValues.class);
                replacableString = "("+criteriaValues.getValues().get(0).toString()+")";

                spannableString.replace(matcher.start(),matcher.end(),replacableString,
                        0, replacableString.length());

            } else if (keyType.equalsIgnoreCase("indicator")) {
                Indicator indicator = gson.fromJson(keyJsonObject.toString(),Indicator.class);
                replacableString = "("+indicator.getDefaultValue()+")";

                spannableString.replace(matcher.start(),matcher.end(),
                        replacableString,0,
                        replacableString.length());
            }
            spannableString.setSpan(clickableSpan,matcher.start(),
                    matcher.start()+replacableString.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    private ClickableSpan getClickableSpan(Criteria criteria, CriteriaItemListener listener, String key) {
        return new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {
                        Toast.makeText(textView.getContext(),"click",Toast.LENGTH_SHORT).show();
                        JsonObject keyJsonObject = criteria.getVariable().getAsJsonObject(key);
                        String keyType = keyJsonObject.get("type").getAsString();
                        if (keyType.equalsIgnoreCase("value")) {
                            CriteriaValues criteriaValues = gson.fromJson(keyJsonObject.toString(),CriteriaValues.class);
                            listener.onCriteriaValueClick(criteriaValues);
                        } else if (keyType.equalsIgnoreCase("indicator")) {
                            Indicator indicator = gson.fromJson(keyJsonObject.toString(),Indicator.class);
                            listener.onCriteriaIndicatorClick(indicator);
                        }
                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(false);
                    }
                };
    }
}
