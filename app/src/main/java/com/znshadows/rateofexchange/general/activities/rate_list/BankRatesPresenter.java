package com.znshadows.rateofexchange.general.activities.rate_list;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;
import com.znshadows.rateofexchange.mvp.presenters.IBankRatesPresenter;
import com.znshadows.rateofexchange.mvp.presenters.IMainPresenter;
import com.znshadows.rateofexchange.mvp.views.IBankRatesView;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by kostya on 01.06.2017.
 */

public class BankRatesPresenter extends BasePresenter<IBankRatesView> implements IBankRatesPresenter<IBankRatesView> {

    @Inject
    IUnifiedModel model;


    public void getBankRates(BANKS bank){
        model.getTodaysList(bank).subscribe(
                getObservable(true, (bankResponse)->getView().showResponce(bankResponse)));
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
