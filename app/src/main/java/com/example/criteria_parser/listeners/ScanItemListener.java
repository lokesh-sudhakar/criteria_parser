package com.example.criteria_parser.listeners;

import com.example.criteria_parser.model.ScanResponse;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public interface ScanItemListener {

    void onItemClick(ScanResponse scanResponse);
}
