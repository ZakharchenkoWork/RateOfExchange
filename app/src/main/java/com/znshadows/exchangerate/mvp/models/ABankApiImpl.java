package com.znshadows.exchangerate.mvp.models;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.znshadows.exchangerate.general.models.UnifiedBankResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Natali Zakharchenko on 19.05.2017.
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
    public Observable<String> getTodayList() {
        return apiInterface.getTodayList()
                .compose(new AsyncTransformer<>());
    }


    @Override
    public Observable<List<UnifiedBankResponse>> getTodayUnifiedList() {
        return getTodayList().map((String responseDTO) -> {

            List<UnifiedBankResponse> mappedResponse = new ArrayList<>();
            String[] parcedData = new String[9];
            int parcedDataIndex = 0;
            String[] data = responseDTO.split("<tbody id='selectByPB'>")[1].split("</tbody>")[0].split("</tr>");
            for (int i = 0; i < data.length; i++) {
                String[] dataParts = data[i].replace("/UAH", "").split(">");
                for (int partIndex = 0; partIndex < dataParts.length; partIndex++) {
                    if (dataParts[partIndex].contains("</td")) {
                        parcedData[parcedDataIndex] = dataParts[partIndex].replace("</td", "");
                        parcedDataIndex++;
                    }
                }
            }

            for (int i = 0; i < parcedData.length; i += 3) {
                mappedResponse.add(new UnifiedBankResponse("", parcedData[i], Double.parseDouble(parcedData[i + 1]), Double.parseDouble(parcedData[i + 2])));
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
