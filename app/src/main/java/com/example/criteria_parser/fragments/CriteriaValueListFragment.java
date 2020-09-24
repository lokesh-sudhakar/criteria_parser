package com.example.criteria_parser.fragments;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.criteria_parser.R;
import com.example.criteria_parser.adapter.ValueListAdapter;
import com.example.criteria_parser.databinding.FragmentListLayoutBinding;
import com.example.criteria_parser.model.CriteriaValues;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class CriteriaValueListFragment extends Fragment {

    private static final String ARG_CRITERIA_VALUES = "arg_criteria_values";
    private FragmentListLayoutBinding binding;
    private CriteriaValues criteriaValues;

    public static CriteriaValueListFragment newInstance(CriteriaValues criteriaValues){
        CriteriaValueListFragment fragment = new CriteriaValueListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CRITERIA_VALUES,criteriaValues);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_layout,container ,false);
        extractData();
        initRecyclerView();
        return binding.getRoot();
    }

    private void initRecyclerView() {
        ValueListAdapter valueListAdapter = new ValueListAdapter(criteriaValues.getValues());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(valueListAdapter);
    }

    private void extractData() {
        if (getArguments()!= null && getArguments().containsKey(ARG_CRITERIA_VALUES)) {
            criteriaValues = (CriteriaValues) getArguments().getSerializable(ARG_CRITERIA_VALUES);
        }
    }
}
