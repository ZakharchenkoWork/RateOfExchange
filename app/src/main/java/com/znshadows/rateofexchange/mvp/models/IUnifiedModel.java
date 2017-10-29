package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Konstantyn Zakharchenko on 24.05.2017.
 */

public interface IUnifiedModel {
    Observable<List<UnifiedBankResponse>> getTodayList(BANKS bank);

    Observable<UnifiedBankResponse> getTodayRates(BANKS bank, String currency);

}
