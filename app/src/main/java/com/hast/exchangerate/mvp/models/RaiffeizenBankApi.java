package com.hast.exchangerate.mvp.models;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by Konstantyn Zakharchenko on 19.05.2017.
 */

public interface RaiffeizenBankApi {
    String URL_START = "https://ex.aval.ua/";

    @GET("en/personal/everyday/exchange/exchange/")
    Observable<String> getTodayList();


}
