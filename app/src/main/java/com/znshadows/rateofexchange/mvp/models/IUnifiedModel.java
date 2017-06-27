package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by kostya on 24.05.2017.
 */

public interface IUnifiedModel {
    Observable<List<UnifiedBankResponce>> getTodaysList(BANKS bank);
    Observable<UnifiedBankResponce> getTodaysRates(BANKS bank, String currency);

}
