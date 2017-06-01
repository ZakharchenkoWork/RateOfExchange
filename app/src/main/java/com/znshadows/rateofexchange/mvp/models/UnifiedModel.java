package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.general.models.nbu.NBUResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.znshadows.rateofexchange.general.models.BANKS.NBU;

/**
 * Created by kostya on 24.05.2017.
 */

public class UnifiedModel implements IUnifiedModel{
    @Inject
    IBaseApi nbuApi;

    public UnifiedModel(){
        App.getAppComponent().inject(this);
    }
    @Override
    public Observable<List<UnifiedBankResponce>> getTodaysList(BANKS bank) {
        switch (bank){
            case NBU:
            return nbuApi.getTodaysUnifiedList();
        }
        return nbuApi.getTodaysUnifiedList();
    }

}
