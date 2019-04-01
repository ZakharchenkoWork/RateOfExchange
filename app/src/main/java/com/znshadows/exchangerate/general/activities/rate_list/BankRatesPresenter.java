package com.znshadows.exchangerate.general.activities.rate_list;

import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.general.activities.BasePresenter;
import com.znshadows.exchangerate.general.models.BANKS;
import com.znshadows.exchangerate.mvp.models.IUnifiedModel;
import com.znshadows.exchangerate.mvp.models.IUserData;
import com.znshadows.exchangerate.mvp.presenters.IBankRatesPresenter;
import com.znshadows.exchangerate.mvp.views.IBankRatesView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Natali Zakharchenko on 01.06.2017.
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
