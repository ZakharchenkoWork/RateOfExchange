package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.general.models.responces.NBUResponse;
import com.znshadows.rateofexchange.general.models.responces.PrivateBankResponce;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Query;

/**
 * Created by kostya on 19.05.2017.
 */

public class PrivateBankApiImpl extends BaseModel implements PrivateBankApi, IBaseApi{

    PrivateBankApi apiInterface;

    public PrivateBankApiImpl() {
        apiInterface = getApiBuilder(URL_START).create(PrivateBankApi.class);
    }


    @Override
    public Observable<List<UnifiedBankResponce>> getTodaysUnifiedList() {
        return getTodaysList(true, "", 4).map((List<PrivateBankResponce> responceDTO) -> {
            List<UnifiedBankResponce> mapedResponse = new ArrayList<>();
            for (PrivateBankResponce pbResponse : responceDTO) {
                mapedResponse.add(new UnifiedBankResponce("", pbResponse.getCode(), pbResponse.getBuy(), pbResponse.getSale()));
            }
            return mapedResponse;
        });
    }

    @Override
    public Observable<UnifiedBankResponce> getTodaysUnifiedRate(String currency) {
        return getTodaysList(true, "", 4).map((List<PrivateBankResponce> responceDTO) -> {

            for (PrivateBankResponce pbResponse : responceDTO) {
                if (pbResponse.getCode().equals(currency)) {
                     return new UnifiedBankResponce("", pbResponse.getCode(), pbResponse.getBuy(), pbResponse.getSale());
                }
            }
            return null;
        });
    }

    @Override
    public Observable<List<PrivateBankResponce>> getTodaysList(@Query("json") boolean isJson, @Query("exchange") String emptyExchange, @Query("coursid") int courseId) {
        return apiInterface.getTodaysList(isJson, emptyExchange,courseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
