package com.znshadows.rateofexchange.general.activities.rate_list;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;
import com.znshadows.rateofexchange.mvp.models.IUserData;
import com.znshadows.rateofexchange.mvp.presenters.IBankRatesPresenter;
import com.znshadows.rateofexchange.mvp.views.IBankRatesView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Konstantyn Zakharchenko on 01.06.2017.
 */

public class BankRatesPresenter extends BasePresenter<IBankRatesView> implements IBankRatesPresenter<IBankRatesView> {

    @Inject
    IUserData userData;
    @Inject
    IUnifiedModel model;

    /**
     * Get bank rates from API of the specified bank.
     * <p>
     * After finish calls {@link IBankRatesView#showResponse(List)}
     *
     * @param bank
     */
    public void getBankRates(BANKS bank) {
        model.getTodayList(bank).subscribe(
                getObserver(true, (bankResponse) -> getView().showResponse(bankResponse)));
    }


    @Override
    public List<String> getChosenCurrencies(BANKS banks) {
        return userData.getChosenCurrencies(banks);
    }

    @Override
    public List<String> addCurrency(BANKS bank, String code) {
        userData.addCurencyToBank(bank, code);
        userData.saveData();
        return getChosenCurrencies(bank);
    }

    @Override
    public List<String> removeCurrency(BANKS bank, String code) {
        userData.removeCurencyFromBank(bank, code);
        userData.saveData();
        return getChosenCurrencies(bank);
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
