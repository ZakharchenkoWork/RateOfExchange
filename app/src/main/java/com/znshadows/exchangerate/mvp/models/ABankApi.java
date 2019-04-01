package com.znshadows.exchangerate.mvp.models;

import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * Created by Natali Zakharchenko on 19.05.2017.
 */

public interface ABankApi {
    String URL_START = "https://privatbank.ua/";

    @GET("ru/currency-a-bank")
    Observable<String> getTodayList();


}
