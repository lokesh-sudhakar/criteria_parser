package com.example.criteria_parser.repository;

import com.example.criteria_parser.model.ScanResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class CriteriaRepository {

    private Retrofit mRetrofit;


    public CriteriaRepository(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }

    public Observable<List<ScanResponse>> getCardsData() {
        return  mRetrofit.create(CriteriaParserAPI.class).getScanData();
    }
}
