package com.hast.exchangerate.mvp.models;

import com.hast.exchangerate.general.models.UnifiedBankResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Konstantyn Zakharchenko on 24.05.2017.
 */

public interface IBaseApi {
    Observable<List<UnifiedBankResponse>> getTodayUnifiedList();

    Observable<UnifiedBankResponse> getTodayUnifiedRate(String currency);
}
