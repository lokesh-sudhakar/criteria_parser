package com.example.criteria_parser.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.criteria_parser.R;
import com.example.criteria_parser.adapter.ScanListingAdapter;
import com.example.criteria_parser.databinding.FragmentListLayoutBinding;
import com.example.criteria_parser.listeners.ScanItemListener;
import com.example.criteria_parser.model.ScanData;
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
    private Context activityContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ListFragmentListener) {
            this.listener = (ListFragmentListener) context;
            this.activityContext = context;
        } else {
            throw new IllegalArgumentException("Activity has not implemented the ListFragmentListener");
        }
    }

    @Override
    public void onItemClick(ScanData scanData) {
        listener.onScanItemClick(scanData);
    }

    public interface ListFragmentListener {
        void onScanItemClick(ScanData scanData);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_layout, container, false);
        viewModel = new ViewModelProvider(this).get(CriteriaParserViewModel.class);
        viewModel.fetchCriteria();
        binding.progressView.setVisibility(View.VISIBLE);
        viewModel.getScanListLiveData().observe(this, this::onScanListResponse);
        return binding.getRoot();
    }

    public void onScanListResponse(ScanResponse scanResponse) {
        binding.progressView.setVisibility(View.GONE);
        if (scanResponse.getErrorMessage() != null) {
            Toast.makeText(getContext(), "" + scanResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
            binding.errorText.setVisibility(View.VISIBLE);
            binding.errorText.setText(scanResponse.getErrorMessage());
            return;
        }
        ScanListingAdapter scanListingAdapter = new ScanListingAdapter(
                scanResponse.getScanDataList(), this);
        binding.recyclerView.setAdapter(scanListingAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activityContext);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.hasFixedSize();
    }


}
