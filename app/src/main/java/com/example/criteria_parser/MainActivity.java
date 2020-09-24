package com.example.criteria_parser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.criteria_parser.databinding.ActivityMainBinding;
import com.example.criteria_parser.fragments.ListFragment;
import com.example.criteria_parser.model.ScanResponse;
import com.example.criteria_parser.viewmodel.CriteriaParserViewModel;

public class MainActivity extends AppCompatActivity implements ListFragment.ListFragmentListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        ListFragment fragment = new ListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(binding.fragmentContainer.getId(),fragment)
                .commit();

    }

    @Override
    public void onScanItemClick(ScanResponse scanResponse) {

    }
}
