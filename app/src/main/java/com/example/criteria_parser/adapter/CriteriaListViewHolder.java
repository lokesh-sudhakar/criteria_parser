package com.example.criteria_parser.adapter;

import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criteria_parser.databinding.CriteriaListItemBinding;
import com.example.criteria_parser.listeners.CriteriaItemListener;
import com.example.criteria_parser.model.Criteria;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class CriteriaListViewHolder  extends RecyclerView.ViewHolder {

    private CriteriaListItemBinding binding;


    public CriteriaListViewHolder(@NonNull CriteriaListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Criteria criteria,boolean isLastItem, CriteriaItemListener listener){
        if (isLastItem) {
            binding.andDelimeter.setVisibility(View.GONE);
        }
        binding.name.setText(criteria.getText());
    }
}
