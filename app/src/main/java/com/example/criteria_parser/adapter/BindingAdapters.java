package com.example.criteria_parser.adapter;

import android.content.Context;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.example.criteria_parser.R;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class BindingAdapters {

    @BindingAdapter({"android:color"})
    public static void setColor(TextView view,  String color) {
        Context context = view.getContext();

        if (color.equalsIgnoreCase("green")) {
            view.setTextColor(context.getResources().getColor(R.color.textColorGreen));
        } else {
            view.setTextColor(context.getResources().getColor(R.color.textColorRed));
        }
    }
}
