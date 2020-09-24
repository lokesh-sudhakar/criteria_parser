package com.example.criteria_parser.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criteria_parser.databinding.ListItemLayoutBinding;
import com.example.criteria_parser.listeners.ScanItemListener;
import com.example.criteria_parser.model.ScanResponse;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class ScanListingViewHolder extends RecyclerView.ViewHolder {

    public ScanResponse scanResponse;
    private ListItemLayoutBinding binding;
    private ScanItemListener listener;

    public ScanListingViewHolder(@NonNull ListItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ScanResponse scanResponse, ScanItemListener listener) {
        this.scanResponse = scanResponse;
        this.listener = listener;
    }
}
