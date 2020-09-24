package com.example.criteria_parser.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criteria_parser.R;
import com.example.criteria_parser.databinding.ValueListItemLayoutBinding;

import java.util.List;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class ValueListAdapter extends RecyclerView.Adapter<ValueListAdapter.ValueListViewHolder> {

    ValueListItemLayoutBinding binding;
    List<Double> values;

    public ValueListAdapter(List<Double> values){
        this.values = values;
    }

    @NonNull
    @Override
    public ValueListAdapter.ValueListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.value_list_item_layout,parent,false);
        return new ValueListAdapter.ValueListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ValueListAdapter.ValueListViewHolder holder, int position) {
        holder.bind(values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ValueListViewHolder extends RecyclerView.ViewHolder {

        private ValueListItemLayoutBinding binding;

        public ValueListViewHolder(@NonNull ValueListItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Double value) {
            binding.setValue(value);
            binding.executePendingBindings();
        }
    }

}
