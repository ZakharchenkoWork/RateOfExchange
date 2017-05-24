package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.nbu.NBUResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by kostya on 19.05.2017.
 */

public interface NBUApi<Type> extends IBaseApi<Type>{
    String URL_START = "https://bank.gov.ua/";
    @Headers({"Accept: application/json;charset=utf-8",
            "Accept-Language:ru-RU,ru;","Content-Type: application/json;charset=utf-8"})
    @GET("/NBUStatService/v1/statdirectory/exchange?json")
    Observable<List<NBUResponse>> getTodaysList();


}
