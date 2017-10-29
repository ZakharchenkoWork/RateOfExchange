package com.znshadows.rateofexchange.general.activities.main;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.ChosenBank;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponse;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;
import com.znshadows.rateofexchange.mvp.models.IUserData;
import com.znshadows.rateofexchange.mvp.presenters.IMainPresenter;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
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

    /**
     * Retrieves banks that was choosen by user. <p>
     * Calls {@link IMainView#showChosenBanks(List)}
     * <p>or<p>
     * Calls {@link IMainView#showNoBanksMessage()}
     */
    @Override
    public void getChosenBanks() {
        ArrayList<ChosenBank> banksList = userData.getBanksList();
        if (banksList != null && banksList.size() > 0) {
            getView().showChosenBanks(banksList);
        } else {
            getView().showNoBanksMessage();
        }
    }

    /**
     * Retrieves rates from specified banks API
     *
     * @param bank                      target
     * @param onBankRatesLoadedListener callback
     */
    @Override
    public void getBankRates(ChosenBank bank, OnBankRatesLoadedListener onBankRatesLoadedListener) {
        model.getTodayList(bank.getBank()).subscribe(
                getObserver(true, (bankResponse) -> {
                    List<UnifiedBankResponse> result = new ArrayList<>();
                    for (int i = 0; i < bankResponse.size(); i++) {
                        if (bank.checkCurrency(bankResponse.get(i).getCode())) {
                            result.add(bankResponse.get(i));
                        }
                    }
                    onBankRatesLoadedListener.onFinish(result);
                }));
    }
}
