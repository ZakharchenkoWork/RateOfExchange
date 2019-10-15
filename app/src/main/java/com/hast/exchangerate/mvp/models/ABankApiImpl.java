package com.hast.exchangerate.mvp.models;

import com.hast.exchangerate.general.models.responces.abank.ABANKResponce;
import com.hast.exchangerate.general.models.responces.abank.Datum;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.hast.exchangerate.general.models.UnifiedBankResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Konstantyn Zakharchenko on 19.05.2017.
 */

public class ABankApiImpl extends BaseModel implements ABankApi, IBaseApi {

    ABankApi apiInterface;

    public ABankApiImpl() {
        apiInterface = getApiBuilder(URL_START).create(ABankApi.class);
    }



    @Override
    public Observable<ABANKResponce> getTodayList() {
        return apiInterface.getTodayList()
                .compose(new AsyncTransformer<>());
    }


    @Override
    public Observable<List<UnifiedBankResponse>> getTodayUnifiedList() {
        return getTodayList().map((ABANKResponce responseDTO) -> {

            List<UnifiedBankResponse> mappedResponse = new ArrayList<>();



            for (Datum dataSell: responseDTO.getData()) {
                if (dataSell.getCcyA().toUpperCase().contains("UAH")){
                    String foreignCode = dataSell.getCcyB().toUpperCase();
                    for (Datum dataBuy : responseDTO.getData()) {
                        if(dataBuy.getCcyA().toUpperCase().contains(foreignCode)){
                            mappedResponse.add(new UnifiedBankResponse("", foreignCode, dataBuy.getRateB(), dataSell.getRateA()));
                            break;
                        }
                    }
                }

            }

            return mappedResponse;
        });
    }

    @Override
    public Observable<UnifiedBankResponse> getTodayUnifiedRate(String currency) {
        return getTodayUnifiedList().map((List<UnifiedBankResponse> unifiedList) -> {
            for (UnifiedBankResponse UnifiedBankResponse : unifiedList) {
                if (UnifiedBankResponse.getCode().equals(currency)) {
                    return UnifiedBankResponse;
                }
            }
            return null;
        });
    }
}
