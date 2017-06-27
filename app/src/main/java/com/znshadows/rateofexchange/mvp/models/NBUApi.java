package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.responces.NBUResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


/**
 * Created by kostya on 19.05.2017.
 */

public interface NBUApi{
    String URL_START = "https://bank.gov.ua/";
    String json = "?json";
    @Headers({"Accept: application/json;charset=utf-8",
            "Accept-Language:ru-RU,ru;","Content-Type: application/json;charset=utf-8"})
    @GET("/NBUStatService/v1/statdirectory/exchange?json")
    Observable<List<NBUResponse>> getTodaysList();

//https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=EUR&date=20170627&json

    @Headers({"Accept: application/json;charset=utf-8",
            "Accept-Language:ru-RU,ru;","Content-Type: application/json;charset=utf-8"})
    @GET("/NBUStatService/v1/statdirectory/exchange?{query}")
    Observable<NBUResponse> getTodaysRate(@Path("query") String query);
}
