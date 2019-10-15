package com.hast.exchangerate.mvp.models;

import com.hast.exchangerate.general.models.UnifiedBankResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Konstantyn Zakharchenko on 19.05.2017.
 */

public class RaiffeizenBankImpl extends BaseModel implements RaiffeizenBankApi, IBaseApi {

    RaiffeizenBankApi apiInterface;

    public RaiffeizenBankImpl() {
        apiInterface = getApiBuilder(URL_START).create(RaiffeizenBankApi.class);
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

            String data = responseDTO.split("</th><th>Buy</th><th>Sell</th></tr><tr>")[1].split("</table>")[0];
            data = data.replace("</tr>", "").replace("<tr>", "");

            String[] parts = data.split("<td class=\"name\">");
            for (int i = 1; i < parts.length; i++) {
                String[] dataParts = parts[i].replace("</td>", "").split("<td class=\"right\">");

                dataParts[0] = normalizeCurrencyName(dataParts[0]);

                mappedResponse.add(new UnifiedBankResponse("", dataParts[0],
                        Double.parseDouble(dataParts[1]),
                        Double.parseDouble(dataParts[2])));
            }

            return mappedResponse;
        });
    }
private String normalizeCurrencyName(String name){
    if (name.contains("Euro")){
        return "EUR";
    }
    if (name.contains("RUR")){
        return "RUB";
    }
    if (name.contains("Swiss Franc")){
        return "CHF";
    }
    return name;
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
