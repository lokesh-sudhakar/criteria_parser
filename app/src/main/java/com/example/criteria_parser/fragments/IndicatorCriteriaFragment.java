package com.example.criteria_parser.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.criteria_parser.R;
import com.example.criteria_parser.databinding.FragmentIndicatorLayoutBinding;
import com.example.criteria_parser.model.Indicator;
import com.example.criteria_parser.utils.BasicUtils;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class IndicatorCriteriaFragment extends Fragment {

    private static final String ARG_INDICATOR = "arg_indicator";
    private FragmentIndicatorLayoutBinding binding;
    private Indicator indicator;

    public static IndicatorCriteriaFragment newInstance(Indicator indicator) {
        IndicatorCriteriaFragment fragment = new IndicatorCriteriaFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_INDICATOR,indicator);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_indicator_layout,container ,false);
        extractData();
        initViews();
        return binding.getRoot();
    }

    private void extractData() {
        if (getArguments()!= null && getArguments().containsKey(ARG_INDICATOR)) {
            indicator = (Indicator) getArguments().getSerializable(ARG_INDICATOR);
        }
    }

    private void initViews() {
        binding.name.setText(indicator.getStudyType().toUpperCase());
        binding.parameterName.setText(BasicUtils.toCamelCase(indicator.getParameterName()));
        binding.parameterValue.setText(""+indicator.getDefaultValue());
    }
}
