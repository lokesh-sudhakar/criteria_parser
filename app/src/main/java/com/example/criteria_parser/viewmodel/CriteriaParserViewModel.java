package com.example.criteria_parser.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.criteria_parser.RetrofitInstance;
import com.example.criteria_parser.model.Criteria;
import com.example.criteria_parser.model.CriteriaValues;
import com.example.criteria_parser.model.Indicator;
import com.example.criteria_parser.model.ScanResponse;
import com.example.criteria_parser.repository.CriteriaRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Lokesh chennamchetty
 * @date 24/09/2020
 */


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CriteriaParserViewModel extends AndroidViewModel {

    private static final String TAG = "criteria";
    private CriteriaRepository repository;
    private CompositeDisposable compositeDisposable;
    private Gson gson;

    public CriteriaParserViewModel(@NonNull Application application) {
        super(application);
        repository = new CriteriaRepository(RetrofitInstance.getRetrofit());
        compositeDisposable = new CompositeDisposable();
         gson= new Gson();

    }

    public void fetchCriteria() {
        compositeDisposable.add(repository.getCardsData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onScanResponse, this::onError));
    }

    public void onScanResponse(List<ScanResponse> response) {
        for (ScanResponse scanResponse : response) {
            Log.d(TAG, "id: " + scanResponse.getId());
            Log.d(TAG, "name: " + scanResponse.getName());
            Log.d(TAG, "tag :" + scanResponse.getTag());
            Log.d(TAG, "color :" + scanResponse.getColor());
            for (Criteria criteria : scanResponse.getCriteria()) {
                Log.d(TAG, "type :" + criteria.getType());
                Log.d(TAG, "text :" + criteria.getText());
                String text = criteria.getText();
                String type = criteria.getType();
                Pattern pattern = Pattern.compile("\\$[(0-9)]+");
                Matcher matcher = pattern.matcher(text);
                List<String> patternKeys = new ArrayList<>();
                while (matcher.find()) {
                    Log.d(TAG, "regex keys: " + matcher.group(0));
                    patternKeys.add(matcher.group(0));
                }
                if (type.equalsIgnoreCase("variable")) {
                    JsonObject vaiableObject = criteria.getVariable();
                    for (String key : patternKeys) {
                        JsonObject keyObject = vaiableObject.getAsJsonObject(key);
                        String keyType = keyObject.get("type").getAsString();
                        if (keyType.equalsIgnoreCase("value")) {
                            CriteriaValues criteriaValues = gson.fromJson(keyObject.toString(),CriteriaValues.class);
                            Log.d(TAG, "default value"+ key+" : " +
                                    Arrays.toString(criteriaValues.getValues().toArray()));
                        } else if (keyType.equalsIgnoreCase("indicator")) {
                            Indicator obj = gson.fromJson(keyObject.toString(),Indicator.class);
                            Log.d(TAG, "default value"+ key+" : " +   obj.getDefaultValue());
                        }
                    }
                }
            }
        }
    }

    public void onError(Throwable throwable) {
        Log.d(TAG, "onError: " + throwable.getMessage());
    }
}
