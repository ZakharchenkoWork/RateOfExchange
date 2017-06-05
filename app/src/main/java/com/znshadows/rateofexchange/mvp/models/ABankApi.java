package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.responces.PrivateBankResponce;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


/**
 * Created by kostya on 19.05.2017.
 */

public interface ABankApi {
    String URL_START = "https://privatbank.ua/";

    @GET("ru/currency-a-bank")
    Observable<String> getTodaysList();


}
