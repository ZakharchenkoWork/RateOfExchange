package com.znshadows.exchangerate.mvp.models;

import com.znshadows.exchangerate.general.models.UnifiedBankResponse;
import com.znshadows.exchangerate.general.models.responces.NBUResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Natali Zakharchenko on 19.05.2017.
 */

public class NBUApiImpl extends BaseModel implements NBUApi, IBaseApi {

    NBUApi apiInterface;

    public NBUApiImpl() {
        apiInterface = getApiBuilder(URL_START).create(NBUApi.class);
    }

    @Override
    public Observable<List<NBUResponse>> getTodayList() {
        return apiInterface.getTodayList()
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
        String date = new SimpleDateFormat("yyyyMMDD").format(new Date(System.currentTimeMillis()));
        return apiInterface.getTodayRate("valcode=" + currency + "date=" + date + apiInterface.json).map((NBUResponse responceDTO) ->
                new UnifiedBankResponse(responceDTO.getName(), responceDTO.getCode(), responceDTO.getRate()));
    }

}
