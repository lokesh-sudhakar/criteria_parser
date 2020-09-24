package com.example.criteria_parser.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criteria_parser.databinding.ListItemLayoutBinding;
import com.example.criteria_parser.listeners.ScanItemListener;
import com.example.criteria_parser.model.ScanData;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class ScanListingViewHolder extends RecyclerView.ViewHolder {

    private ListItemLayoutBinding binding;

    public ScanListingViewHolder(@NonNull ListItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ScanData scanData, ScanItemListener listener) {
        binding.setScanData(scanData);
        binding.setListener(listener);
        binding.executePendingBindings();
    }
}
