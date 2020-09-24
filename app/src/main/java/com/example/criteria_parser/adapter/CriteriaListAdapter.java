package com.example.criteria_parser.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criteria_parser.R;
import com.example.criteria_parser.databinding.CriteriaListItemBinding;
import com.example.criteria_parser.listeners.CriteriaItemListener;
import com.example.criteria_parser.model.Criteria;

import java.util.List;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class CriteriaListAdapter extends RecyclerView.Adapter<CriteriaListViewHolder> {

    private CriteriaListItemBinding binding;
    private List<Criteria> criteriaList;
    private CriteriaItemListener listener;

    public CriteriaListAdapter(List<Criteria> criteriaList, CriteriaItemListener listener) {
        this.criteriaList = criteriaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CriteriaListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.criteria_list_item, parent, false);
        return new CriteriaListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CriteriaListViewHolder holder, int position) {
        holder.bind(criteriaList.get(position), position == criteriaList.size() - 1, listener);
    }

    @Override
    public int getItemCount() {
        return criteriaList.size();
    }
}
