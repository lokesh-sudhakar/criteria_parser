package com.example.criteria_parser;

import android.app.Application;

import com.example.criteria_parser.di.AppComponent;
import com.example.criteria_parser.di.DaggerAppComponent;

/**
 * @author Lokesh chennamchetty
 * @date 25/09/2020
 */
public class App extends Application {

    static App instance;
    private AppComponent activityComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getApplicationInstance(){
        return  instance;
    }


    public AppComponent getAppComponent(){
        if (activityComponent ==null) {
            activityComponent = DaggerAppComponent.create();
        }
        return activityComponent;
    }
}
