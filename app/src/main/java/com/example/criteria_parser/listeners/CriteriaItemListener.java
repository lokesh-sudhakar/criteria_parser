package com.example.criteria_parser.listeners;

import com.example.criteria_parser.model.CriteriaValues;
import com.example.criteria_parser.model.Indicator;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public interface CriteriaItemListener {

    void onCriteriaValueClick(CriteriaValues criteriaValues);

    void onCriteriaIndicatorClick(Indicator indicator);
}
