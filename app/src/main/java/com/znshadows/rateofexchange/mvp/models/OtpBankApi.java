package com.znshadows.rateofexchange.mvp.models;

import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * Created by Konstantyn Zakharchenko on 19.05.2017.
 */

public interface OtpBankApi {
    String URL_START = "https://alfabank.ua/";

    @GET("/")
    Observable<String> getTodayList();


}
