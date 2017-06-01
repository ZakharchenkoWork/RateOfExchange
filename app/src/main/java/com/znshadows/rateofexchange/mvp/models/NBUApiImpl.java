package com.znshadows.rateofexchange.mvp.models;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.general.models.nbu.NBUResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kostya on 19.05.2017.
 */

public class NBUApiImpl extends BaseModel implements NBUApi, IBaseApi{

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
    public Observable<List<UnifiedBankResponce>> getTodaysUnifiedList() {
        return getTodaysList().map((List<NBUResponse> responceDTO) -> {
            List<UnifiedBankResponce> mapedResponse = new ArrayList<>();
            for (NBUResponse nbuResponse : responceDTO) {
                mapedResponse.add(new UnifiedBankResponce(nbuResponse.getName(), nbuResponse.getCode(), nbuResponse.getRate()));
            }
            return mapedResponse;
        });
    }
}
