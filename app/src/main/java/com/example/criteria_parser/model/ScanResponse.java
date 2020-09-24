package com.example.criteria_parser.model;

import java.util.List;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class ScanResponse {

    private List<ScanData> scanDataList;

    public String getErrorMessage() {
        return errorMessage;
    }

    private String errorMessage ;


    public ScanResponse(List<ScanData> scanDataList) {
        this.scanDataList = scanDataList;
    }

    public ScanResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<ScanData> getScanDataList() {
        return scanDataList;
    }
}
