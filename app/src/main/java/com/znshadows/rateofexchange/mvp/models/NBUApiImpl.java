package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.general.models.responces.NBUResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Path;

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
                .compose(new AsyncTransformer<>());
    }

    @Override
    public Observable<NBUResponse> getTodaysRate(String query) {
        return apiInterface.getTodaysRate(query)
                .compose(new AsyncTransformer<>());
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

    @Override
    public Observable<UnifiedBankResponce> getTodaysUnifiedRate(String currency) {
        //valcode=EUR&date=20170627
        String date = new SimpleDateFormat("yyyyMMDD").format(new Date(System.currentTimeMillis()));
        return apiInterface.getTodaysRate("valcode="+currency+"date="+date+apiInterface.json).map((NBUResponse responceDTO)->
                     new UnifiedBankResponce(responceDTO.getName(), responceDTO.getCode(), responceDTO.getRate()));
    }

}
