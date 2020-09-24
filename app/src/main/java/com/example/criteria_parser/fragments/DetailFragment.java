package com.example.criteria_parser.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.criteria_parser.R;
import com.example.criteria_parser.adapter.CriteriaListAdapter;
import com.example.criteria_parser.databinding.FragmentDetailLayoutBinding;
import com.example.criteria_parser.listeners.CriteriaItemListener;
import com.example.criteria_parser.model.Criteria;
import com.example.criteria_parser.model.CriteriaValues;
import com.example.criteria_parser.model.Indicator;
import com.example.criteria_parser.model.ScanData;

import java.util.Arrays;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class DetailFragment extends Fragment implements CriteriaItemListener {

    private static final String TAG = "criteria";
    public static final String ARG_SCAN_DATE = "arg_scan_date";
    private FragmentDetailLayoutBinding binding;
    private ScanData scanData;


    public static DetailFragment newInstance(ScanData scanData) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_SCAN_DATE, scanData);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    public DetailFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_layout,container,false);
        extractData();
        initView();
        initRecyclerView();
        return  binding.getRoot();
    }

    private void initView() {
        binding.setScanData(scanData);
    }

    private void initRecyclerView() {
        CriteriaListAdapter criteriaListAdapter = new CriteriaListAdapter(scanData.getCriteria(),this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(criteriaListAdapter);
    }

    private void extractData() {
        if (getArguments() != null && getArguments().containsKey(ARG_SCAN_DATE)) {
            scanData = (ScanData) getArguments().getSerializable(ARG_SCAN_DATE);
        }
    }

    @Override
    public void onCriteriaValueClick(CriteriaValues criteriaValues) {
        Log.d(TAG, "default value : " +
                Arrays.toString(criteriaValues.getValues().toArray()));
    }

    @Override
    public void onCriteriaIndicatorClick(Indicator indicator) {
        Log.d(TAG, "default value" +   indicator.getDefaultValue());
    }
}
