package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.responces.NBUResponse;
import com.znshadows.rateofexchange.general.models.responces.PrivateBankResponce;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


/**
 * Created by kostya on 19.05.2017.
 */

public interface PrivateBankApi {
    String URL_START = "https://api.privatbank.ua/";
    @Headers({"Accept: application/json;charset=utf-8",
            "Accept-Language:ru-RU,ru;","Content-Type: application/json;charset=utf-8"})
    @GET("p24api/pubinfo")
    Observable<List<PrivateBankResponce>> getTodaysList(@Query("json") boolean isJson, @Query("exchange")String emptyExchange, @Query("coursid") int courseId);


}
