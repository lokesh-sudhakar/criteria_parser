package com.example.criteria_parser.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.criteria_parser.model.BaseCriteria;
import com.example.criteria_parser.model.Criteria;
import com.example.criteria_parser.model.CriteriaValues;
import com.example.criteria_parser.model.Indicator;
import com.example.criteria_parser.retrofit.RetrofitInstance;
import com.example.criteria_parser.model.ScanData;
import com.example.criteria_parser.model.ScanResponse;
import com.example.criteria_parser.repository.CriteriaRepository;
import com.example.criteria_parser.utils.BasicUtils;
import com.example.criteria_parser.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private void onScanResponse(List<ScanData> scanDataList) {
        scanListLiveData.setValue(new ScanResponse(parseCriterias(scanDataList)));
    }

    private void onError(Throwable throwable) {
        scanListLiveData.setValue(new ScanResponse(throwable.getMessage()));
        Log.d(TAG, "onError: " + throwable.getMessage());
    }

    private List<ScanData> parseCriterias(List<ScanData> scanDataList) {
        Gson gson = new Gson();
        for (ScanData scanData : scanDataList) {
            for (Criteria criteria : scanData.getCriteria()) {
                String text = criteria.getText();
                String type = criteria.getType();
                Matcher matcher = Pattern.compile(Constants.Regex.DOLLAR_REGEX).matcher(text);
                List<String> patternKeys = new ArrayList<>();
                while (matcher.find()) {
                    patternKeys.add(matcher.group(0));
                }
                if (type.equalsIgnoreCase(Constants.Type.VARIABLE)) {
                    HashMap<String, BaseCriteria> parsedVariable = new HashMap<>();
                    for (String key : patternKeys) {
                        JsonObject keyObject = criteria.getVariable().get(key);
                        String keyType = keyObject.get(Constants.Keys.TYPE).getAsString();
                        if (keyType.equalsIgnoreCase(Constants.Type.VALUE)) {
                            CriteriaValues criteriaValues = gson.fromJson(keyObject.toString(), CriteriaValues.class);
                            parsedVariable.put(key, criteriaValues);
                        } else if (keyType.equalsIgnoreCase(Constants.Type.INDICATOR)) {
                            Indicator indicator = gson.fromJson(keyObject.toString(), Indicator.class);
                            parsedVariable.put(key, indicator);
                        }
                    }
                    criteria.setParsedVariable(parsedVariable);
                }
            }
        }
        return scanDataList;
    }
}
