package com.hast.exchangerate.mvp.models;

import com.hast.exchangerate.general.models.UnifiedBankResponse;
import com.hast.exchangerate.general.models.responces.NBUResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Konstantyn Zakharchenko on 19.05.2017.
 */

public class NBUApiImpl extends BaseModel implements NBUApi, IBaseApi {

    NBUApi apiInterface;

    public NBUApiImpl() {
        apiInterface = getApiBuilder(URL_START).create(NBUApi.class);
    }
    //https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date=20170627&json
    @Override
    public Observable<List<NBUResponse>> getTodayList() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
        return getTodayList(date+apiInterface.json);
    }
    @Override
    public Observable<List<NBUResponse>> getTodayList(String query) {
        return apiInterface.getTodayList(query)
                .compose(new AsyncTransformer<>());
    }

    @Override
    public Observable<NBUResponse> getTodayRate(String query) {
        return apiInterface.getTodayRate(query)
                .compose(new AsyncTransformer<>());
    }


    @Override
    public Observable<List<UnifiedBankResponse>> getTodayUnifiedList() {
        return getTodayList().map((List<NBUResponse> responseDTO) -> {
            List<UnifiedBankResponse> mappedResponse = new ArrayList<>();
            for (NBUResponse nbuResponse : responseDTO) {
                mappedResponse.add(new UnifiedBankResponse(nbuResponse.getName(), nbuResponse.getCode(), nbuResponse.getRate()));
            }
            return mappedResponse;
        });
    }

    @Override
    public Observable<UnifiedBankResponse> getTodayUnifiedRate(String currency) {
        //valcode=EUR&date=20170627
        //https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=EUR&date=20170627&json
        String date = new SimpleDateFormat("yyyyMMDD").format(new Date(System.currentTimeMillis()));
        return apiInterface.getTodayRate("valcode=" + currency + "date=" + date + apiInterface.json).map((NBUResponse responceDTO) ->
                new UnifiedBankResponse(responceDTO.getName(), responceDTO.getCode(), responceDTO.getRate()));
    }

}
