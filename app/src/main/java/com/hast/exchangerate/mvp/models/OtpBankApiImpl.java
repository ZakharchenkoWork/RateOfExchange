package com.hast.exchangerate.mvp.models;

import com.hast.exchangerate.general.models.responces.PrivateBankResponse;
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

public class OtpBankApiImpl extends BaseModel implements OtpBankApi, IBaseApi {

    OtpBankApi apiInterface;

    public OtpBankApiImpl() {
        apiInterface = getApiBuilder(URL_START).create(OtpBankApi.class);
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

            String[] data = responseDTO.split("<td class=\"first_column\">");
            String[] parts = {data[1].split("</tr>")[0],
                    data[2].split("</tr>")[0],
                    data[3].split("</tr>")[0]};
            for (String part : parts) {
                String[] dataParts = part.split("</td>");

                String buy = dataParts[1].replace("<td>", "").trim();
                String sell = dataParts[2].replace("<td>", "").trim();
                mappedResponse.add(new UnifiedBankResponse("", dataParts[0].trim(),
                        Double.parseDouble(buy),
                        Double.parseDouble(sell)));
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
