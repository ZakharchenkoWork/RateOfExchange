package com.hast.exchangerate.mvp.models;

import com.hast.exchangerate.general.models.BANKS;
import com.hast.exchangerate.general.models.UnifiedBankResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Konstantyn Zakharchenko on 24.05.2017.
 */

public interface IUnifiedModel {
    Observable<List<UnifiedBankResponse>> getTodayList(BANKS bank);

    Observable<UnifiedBankResponse> getTodayRates(BANKS bank, String currency);

}
