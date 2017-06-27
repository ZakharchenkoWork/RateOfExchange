package com.znshadows.rateofexchange.mvp.models;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.general.models.responces.NBUResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by kostya on 19.05.2017.
 */

public class ABankApiImpl extends BaseModel implements ABankApi, IBaseApi {

    ABankApi apiInterface;

    public ABankApiImpl() {
        apiInterface = getApiBuilder(URL_START).create(ABankApi.class);
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
    public Observable<String> getTodaysList() {
        return apiInterface.getTodaysList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<List<UnifiedBankResponce>> getTodaysUnifiedList() {
        return getTodaysList().map((String responceDTO) -> {

            List<UnifiedBankResponce> mapedResponse = new ArrayList<>();
            String[] parcedData = new String[9];
            int parcedDataIndex = 0;
            String[] data = responceDTO.split("<tbody id='selectByPB'>")[1].split("</tbody>")[0].split("</tr>");
            for (int i = 0; i < data.length; i++) {
                String[] dataParts = data[i].replace("/UAH", "").split(">");
                for (int partIndex = 0; partIndex < dataParts.length; partIndex++) {
                    if(dataParts[partIndex].contains("</td")){
                        parcedData[parcedDataIndex] = dataParts[partIndex].replace("</td", "");
                        parcedDataIndex++;
                    }
                }
            }

            for (int i = 0; i < parcedData.length; i+=3) {
                mapedResponse.add(new UnifiedBankResponce("", parcedData[i], Double.parseDouble(parcedData[i+1]), Double.parseDouble(parcedData[i+2])));
            }
            return mapedResponse;
        });
    }

    @Override
    public Observable<UnifiedBankResponce> getTodaysUnifiedRate(String currency) {
        return getTodaysUnifiedList().map((List<UnifiedBankResponce> unifiedList)->{
            for (UnifiedBankResponce unifiedBankResponce : unifiedList) {
                if(unifiedBankResponce.getCode().equals(currency)){
                    return unifiedBankResponce;
                }
            }
            return null;
        });
    }
}
