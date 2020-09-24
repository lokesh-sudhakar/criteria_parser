package com.example.criteria_parser.di;

import com.example.criteria_parser.viewmodel.CriteriaParserViewModel;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * @author Lokesh chennamchetty
 * @date 25/09/2020
 */

@Singleton
@Component(modules = MainModule.class)
public interface AppComponent {

    Retrofit getRetrofit();

    void inject(CriteriaParserViewModel viewModel);

}
