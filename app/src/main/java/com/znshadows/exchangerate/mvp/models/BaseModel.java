package com.znshadows.exchangerate.mvp.models;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Natali Zakharchenko on 24.05.2017.
 */

abstract class BaseModel {
    protected Retrofit getApiBuilder(String baseUrl) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .build();
    }

    protected OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }
}
