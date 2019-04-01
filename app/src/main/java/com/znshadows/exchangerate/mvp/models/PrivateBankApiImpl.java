package com.znshadows.exchangerate.mvp.models;

import com.znshadows.exchangerate.general.models.UnifiedBankResponse;
import com.znshadows.exchangerate.general.models.responces.PrivateBankResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by Natali Zakharchenko on 19.05.2017.
 */

public class PrivateBankApiImpl extends BaseModel implements PrivateBankApi, IBaseApi {

    PrivateBankApi apiInterface;

    public PrivateBankApiImpl() {
        apiInterface = getApiBuilder(URL_START).create(PrivateBankApi.class);
    }

    @Override
    public Observable<List<PrivateBankResponse>> getTodayList(@Query("json") boolean isJson, @Query("exchange") String emptyExchange, @Query("coursid") int courseId) {
        return apiInterface.getTodayList(isJson, emptyExchange, courseId)
                .compose(new AsyncTransformer<>());
    }

    @Override
    public Observable<List<UnifiedBankResponse>> getTodayUnifiedList() {
        return getTodayList(true, "", 4).map((List<PrivateBankResponse> responseDTO) -> {
            List<UnifiedBankResponse> mappedResponse = new ArrayList<>();
            for (PrivateBankResponse pbResponse : responseDTO) {
                mappedResponse.add(new UnifiedBankResponse("", pbResponse.getCode(), pbResponse.getBuy(), pbResponse.getSale()));
            }
            return mappedResponse;
        });
    }

    @Override
    public Observable<UnifiedBankResponse> getTodayUnifiedRate(String currency) {
        return getTodayList(true, "", 4).map((List<PrivateBankResponse> responceDTO) -> {

            for (PrivateBankResponse pbResponse : responceDTO) {
                if (pbResponse.getCode().equals(currency)) {
                    return new UnifiedBankResponse("", pbResponse.getCode(), pbResponse.getBuy(), pbResponse.getSale());
                }
            }
            return null;
        });
    }


}
