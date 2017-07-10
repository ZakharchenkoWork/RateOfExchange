package com.znshadows.rateofexchange.general.activities.main;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.ChoosenBank;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;

import com.znshadows.rateofexchange.mvp.models.IUserData;
import com.znshadows.rateofexchange.mvp.models.UnifiedModel;
import com.znshadows.rateofexchange.mvp.presenters.IMainPresenter;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by kostya on 17.05.2017.
 */

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter<IMainView> {

    @Inject
    IUnifiedModel model;
    @Inject
    IUserData userData;

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }


    @Override
    public void getChoosenBanks() {
        getView().showChoosenBanks(userData.getBanksList());
    }
    /**
     * Retrieves rates from specified banks API
     * @param bank target
     * @param onBankRatesLoadedListener callback
     */
    @Override
    public void getBankRates(ChoosenBank bank, OnBankRatesLoadedListener onBankRatesLoadedListener) {
        model.getTodaysList(bank.getBank()).subscribe(
                getObserver(true, (bankResponse) -> {
                    List<UnifiedBankResponce> result = new ArrayList<>();
                    for (int i = 0; i < bankResponse.size(); i++) {
                        if (bank.checkCurrency(bankResponse.get(i).getCode())) {
                            result.add(bankResponse.get(i));
                        }
                    }
                    onBankRatesLoadedListener.onFinish(result);
                }));
    }
}
