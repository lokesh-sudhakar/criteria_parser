package com.example.criteria_parser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.criteria_parser.databinding.ActivityMainBinding;
import com.example.criteria_parser.fragments.CriteriaValueListFragment;
import com.example.criteria_parser.fragments.DetailFragment;
import com.example.criteria_parser.fragments.ListFragment;
import com.example.criteria_parser.model.CriteriaValues;
import com.example.criteria_parser.model.Indicator;
import com.example.criteria_parser.model.ScanData;

public class MainActivity extends AppCompatActivity implements ListFragment.ListFragmentListener,
    DetailFragment.CriteriaClickListeners{

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
    public void onScanItemClick(ScanData scanData) {
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainer.getId(), DetailFragment.newInstance(scanData))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCriteriaValueClick(CriteriaValues criteriaValues) {
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainer.getId(), CriteriaValueListFragment.newInstance(criteriaValues))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCriteriaIndicatorClick(Indicator indicator) {

    }
}
