package com.hast.exchangerate.mvp.models;

import com.hast.exchangerate.general.models.UnifiedBankResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Konstantyn Zakharchenko on 19.05.2017.
 */

public class UkrsibbankApiImpl extends BaseModel implements UkrsibbankApi, IBaseApi {

    UkrsibbankApi apiInterface;

    public UkrsibbankApiImpl() {
        apiInterface = getApiBuilder(URL_START).create(UkrsibbankApi.class);
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

            String data[] = responseDTO.split("<span class=\"rate__mob\">Продажа</span>")[1]
                    .split("<div class=\"rate__more\"><a href=\"/ru/personal/operations/currency_exchange/\"")[0]
                    .split("class=\"rate__val ");


            //Log.d("data", data);
            for (int i = 1; i < data.length; i++) {

                String[] parts = data[i].split("<span class=\"rate__mob\">");
                parts[0] = parts[0].replace("active", "").replace("\">", "").replace("</a>", "").trim();
                parts[1] = parts[1].replace("</span>", "").split("<i class=\"i-up\">")[0].trim();
                parts[2] = parts[2].split("<i class=\"i-up\">")[0].trim();


                mappedResponse.add(new UnifiedBankResponse("", parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2])));
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
