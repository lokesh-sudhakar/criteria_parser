package com.example.criteria_parser.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.criteria_parser.R;
import com.example.criteria_parser.databinding.FragmentListLayoutBinding;
import com.example.criteria_parser.listeners.ScanItemListener;
import com.example.criteria_parser.model.ScanResponse;
import com.example.criteria_parser.viewmodel.CriteriaParserViewModel;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class ListFragment extends Fragment implements ScanItemListener {

    private FragmentListLayoutBinding binding;
    private CriteriaParserViewModel viewModel;
    private ListFragmentListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ListFragmentListener) {
            this.listener = (ListFragmentListener)context;
        } else {
            throw new IllegalArgumentException("Activity has not implemented the ListFragmentListener");
        }
    }

    @Override
    public void onItemClick(ScanResponse scanResponse) {
        listener.onScanItemClick(scanResponse);
    }

    public interface ListFragmentListener {
        void onScanItemClick(ScanResponse scanResponse);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list_layout,container ,false);
        viewModel = new ViewModelProvider(this).get(CriteriaParserViewModel.class);
        viewModel.fetchCriteria();
        return binding.getRoot();
    }



}
