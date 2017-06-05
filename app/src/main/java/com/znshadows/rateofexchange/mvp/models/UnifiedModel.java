package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

/**
 * Created by kostya on 24.05.2017.
 */

public class UnifiedModel implements IUnifiedModel{
    @Inject @Named("nbu")
    IBaseApi nbuApi;
    @Inject @Named("pb")
    IBaseApi pbApi;
    @Inject @Named("abank")
    IBaseApi abankApi;
    
    public UnifiedModel(){
        App.getAppComponent().inject(this);
    }
    @Override
    public Observable<List<UnifiedBankResponce>> getTodaysList(BANKS bank) {
        switch (bank){
            case NBU:
            return nbuApi.getTodaysUnifiedList();
            case PRIVATE_BANK:
                return pbApi.getTodaysUnifiedList();
            case A_BANK:
                return abankApi.getTodaysUnifiedList();
        }
        return nbuApi.getTodaysUnifiedList();
    }

}
