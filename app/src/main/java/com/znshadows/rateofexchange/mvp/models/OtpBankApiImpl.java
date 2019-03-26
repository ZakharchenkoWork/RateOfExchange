package com.znshadows.rateofexchange.mvp.models;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Konstantyn Zakharchenko on 19.05.2017.
 */

public class AlfabankApiImpl extends BaseModel implements AlfabankApi, IBaseApi {

    AlfabankApi apiInterface;

    public AlfabankApiImpl() {
        apiInterface = getApiBuilder(URL_START).create(AlfabankApi.class);
    }

    @Override
    protected Retrofit getApiBuilder(String baseUrl) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .build();
    }

    @Override
    public Observable<String> getTodayList() {
        return apiInterface.getTodayList()
                .compose(new AsyncTransformer<>());
    }


    @Override
    public Observable<List<UnifiedBankResponse>> getTodayUnifiedList() {
        return getTodayList().map((String responseDTO) -> {
            //Log.d("data", responseDTO);
            List<UnifiedBankResponse> mappedResponse = new ArrayList<>();

            String data[] = responseDTO.split("<div class=\"currency-tab-block\" data-tab=\"0\">")[1].split("</section>")[0].split("<div class=\"currency-block\">");


            //Log.d("data", data);
           for (int i = 1; i < 4; i++) {

               String[] parts = data[i].split("</div>");
               String currency = parts[0].replace("<div class=\"title\">", "").trim();
               String buy = parts[1].replace("<div class=\"rate\">", "").trim();
               String sell = parts[2].replace("<div class=\"rate\">", "").trim();

               mappedResponse.add(new UnifiedBankResponse("", currency, Double.parseDouble(buy), Double.parseDouble(sell)));
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
