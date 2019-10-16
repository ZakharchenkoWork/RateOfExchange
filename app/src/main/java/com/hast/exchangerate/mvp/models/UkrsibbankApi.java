package com.hast.exchangerate.mvp.models;

import com.hast.exchangerate.general.models.responces.abank.ABANKResponce;

import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * Created by Konstantyn Zakharchenko on 19.05.2017.
 */

public interface UkrsibbankApi {
    String URL_START = "https://my.ukrsibbank.com/";
//a-bank.com.ua/backend/api/v1/rates
    @GET("ru/personal/")
    Observable<String> getTodayList();


}
