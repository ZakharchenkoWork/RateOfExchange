package com.znshadows.rateofexchange.general.activities.choose_bank;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.models.IDatabaseManager;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;
import com.znshadows.rateofexchange.mvp.models.IUserData;
import com.znshadows.rateofexchange.mvp.presenters.IChooseBankPresenter;
import com.znshadows.rateofexchange.mvp.views.IChooseBankView;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by kostya on 17.05.2017.
 */

public class ChooseBankPresenter extends BasePresenter<IChooseBankView> implements IChooseBankPresenter<IChooseBankView> {

    @Inject
    IUserData userData;

    /**
     * @return list of all banks(whithout settings) that was choosen by user.
     */
    @Override
    public List<BANKS> getChosenBanks() {
        return userData.getRawBanksList();
    }

    /**
     * Add's banks that wasn't in the list before, banks that was on the list and still there,
     * keeps their settings, old banks that are not in the list anymore, gets deleted.
     * Also saves this data to the DB.
     * @param banks list of banks to save
     */
    @Override
    public void saveChosenBanks(List<BANKS> banks) {
        userData.setChoosenBanks(banks);
        userData.saveData();
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
