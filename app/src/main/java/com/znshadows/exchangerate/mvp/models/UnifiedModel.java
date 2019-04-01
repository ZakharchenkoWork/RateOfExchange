package com.znshadows.exchangerate.mvp.models;

import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.general.models.BANKS;
import com.znshadows.exchangerate.general.models.UnifiedBankResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

/**
 * Created by Natali Zakharchenko on 24.05.2017.
 */

public class UnifiedModel implements IUnifiedModel {
    @Inject
    @Named("nbu")
    IBaseApi nbuApi;
    @Inject
    @Named("pb")
    IBaseApi pbApi;
    @Inject
    @Named("abank")
    IBaseApi abankApi;

    public UnifiedModel() {
        App.getAppComponent().inject(this);
    }

    @Override
    public Observable<List<UnifiedBankResponse>> getTodayList(BANKS bank) {
        return getBankApi(bank).getTodayUnifiedList();
    }

    @Override
    public Observable<UnifiedBankResponse> getTodayRates(BANKS bank, String currency) {
        return getBankApi(bank).getTodayUnifiedRate(currency);
    }

    private IBaseApi getBankApi(BANKS bank) {
        switch (bank) {
            case NBU:
                return nbuApi;
            case PRIVATE_BANK:
                return pbApi;
            case A_BANK:
                return abankApi;
        }
        return nbuApi;
    }
}
