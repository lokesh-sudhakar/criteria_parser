package com.example.criteria_parser.repository;

import com.example.criteria_parser.model.ScanData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */
public class CriteriaRepository {

    private Retrofit mRetrofit;

    @Inject
    public CriteriaRepository(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }

    public Observable<List<ScanData>> getCardsData() {
        return mRetrofit.create(CriteriaParserAPI.class).getScanData();
    }
}
