package com.znshadows.rateofexchange.mvp.models;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.general.models.nbu.NBUResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kostya on 19.05.2017.
 */

public class NBUApiImpl extends BaseModel implements NBUApi<NBUResponse> {

    NBUApi apiInterface;

    public NBUApiImpl() {
        apiInterface = getApiBuilder(URL_START).create(NBUApi.class);
    }

    @Override
    public Observable<List<NBUResponse>> getTodaysList() {
        return apiInterface.getTodaysList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public UnifiedBankResponce mapResponce(NBUResponse responceDTO) {
        return new UnifiedBankResponce(responceDTO.getName(),responceDTO.getCode(), responceDTO.getRate());
    }

}
