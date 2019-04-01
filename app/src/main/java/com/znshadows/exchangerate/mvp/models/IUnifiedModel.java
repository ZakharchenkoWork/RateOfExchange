package com.znshadows.exchangerate.mvp.models;

import com.znshadows.exchangerate.general.models.BANKS;
import com.znshadows.exchangerate.general.models.UnifiedBankResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Natali Zakharchenko on 24.05.2017.
 */

public interface IUnifiedModel {
    Observable<List<UnifiedBankResponse>> getTodayList(BANKS bank);

    Observable<UnifiedBankResponse> getTodayRates(BANKS bank, String currency);

}
