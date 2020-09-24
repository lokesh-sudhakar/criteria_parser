package com.example.criteria_parser.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.criteria_parser.RetrofitInstance;
import com.example.criteria_parser.model.ScanData;
import com.example.criteria_parser.model.ScanResponse;
import com.example.criteria_parser.repository.CriteriaRepository;
import com.example.criteria_parser.utils.BasicUtils;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */

public class CriteriaParserViewModel extends AndroidViewModel {

    private static final String TAG = "criteria";
    private CriteriaRepository repository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<ScanResponse> scanListLiveData = new MutableLiveData<>();

    public CriteriaParserViewModel(@NonNull Application application) {
        super(application);
        repository = new CriteriaRepository(RetrofitInstance.getRetrofit());
        compositeDisposable = new CompositeDisposable();
    }

    public void fetchCriteria() {
        compositeDisposable.add(repository.getCardsData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onScanResponse, this::onError));
    }

    public MutableLiveData<ScanResponse> getScanListLiveData() {
        return scanListLiveData;
    }

    public void onScanResponse(List<ScanData> scanDataList) {
        scanListLiveData.postValue(new ScanResponse(BasicUtils.parseCriterias(scanDataList)));
    }

    public void onError(Throwable throwable) {
        scanListLiveData.postValue(new ScanResponse(throwable.getMessage()));
        Log.d(TAG, "onError: " + throwable.getMessage());
    }
}
