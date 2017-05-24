package com.znshadows.rateofexchange.mvp.models;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kostya on 24.05.2017.
 */

abstract class BaseModel implements IBaseApi {
    protected Retrofit getApiBuilder(String baseUrl) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();

    }

    @Override
    public List<UnifiedBankResponce> mapResponceList(List responceDTO) {
        List<UnifiedBankResponce> responseResultList= new ArrayList<>();

        for (int i = 0; i < responceDTO.size(); i++) {
            responseResultList.add(mapResponce(responceDTO.get(i)));
        }
        return responseResultList;
    }
}