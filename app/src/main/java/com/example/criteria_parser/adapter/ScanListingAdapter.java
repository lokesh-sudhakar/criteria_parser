package com.example.criteria_parser.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criteria_parser.R;
import com.example.criteria_parser.databinding.ListItemLayoutBinding;
import com.example.criteria_parser.listeners.ScanItemListener;
import com.example.criteria_parser.model.ScanData;

import java.util.List;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class ScanListingAdapter extends RecyclerView.Adapter<ScanListingViewHolder> {

    private List<ScanData> scanList;
    private ListItemLayoutBinding binding;
    private ScanItemListener listener;

    public ScanListingAdapter(List<ScanData> scanList, ScanItemListener listener) {
        this.scanList = scanList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ScanListingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_item_layout, parent, false);
        return new ScanListingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ScanListingViewHolder holder, int position) {
        holder.bind(scanList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return scanList.size();
    }
}
