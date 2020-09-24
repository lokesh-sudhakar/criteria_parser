package com.example.criteria_parser.repository;

import com.example.criteria_parser.model.ScanResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public interface CriteriaParserAPI {

    @GET(ApiUrls.GET_DATA)
    Observable<List<ScanResponse>> getScanData();

}
