package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by kostya on 24.05.2017.
 */

public interface IBaseApi {
    Observable<List<UnifiedBankResponce>> getTodaysUnifiedList();

    Observable<UnifiedBankResponce> getTodaysUnifiedRate(String currency);
}
