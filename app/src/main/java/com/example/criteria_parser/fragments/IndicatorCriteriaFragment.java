package com.example.criteria_parser.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.criteria_parser.model.Indicator;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class IndicatorCriteriaFragment extends Fragment {

    public static final String ARG_INDICATOR = "arg_indicator";
    private Indicator indicator;

    public static IndicatorCriteriaFragment newInstance(Indicator indicator) {
        IndicatorCriteriaFragment fragment = new IndicatorCriteriaFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_INDICATOR,indicator);
        fragment.setArguments(bundle);
        return new IndicatorCriteriaFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
