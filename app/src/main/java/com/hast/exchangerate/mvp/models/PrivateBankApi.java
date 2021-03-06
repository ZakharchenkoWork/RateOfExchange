package com.hast.exchangerate.mvp.models;

import com.hast.exchangerate.general.models.responces.PrivateBankResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


/**
 * Created by Konstantyn Zakharchenko on 19.05.2017.
 */

public interface PrivateBankApi {
    String URL_START = "https://api.privatbank.ua/";

    @Headers({"Accept: application/json;charset=utf-8",
            "Accept-Language:ru-RU,ru;", "Content-Type: application/json;charset=utf-8"})
    //https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5
    @GET("p24api/pubinfo")
    Observable<List<PrivateBankResponse>> getTodayList(@Query("json") boolean isJson, @Query("exchange") String emptyExchange, @Query("coursid") int courseId);


}
