package com.hast.exchangerate.general.activities.choose_bank;

import com.hast.exchangerate.App;
import com.hast.exchangerate.general.activities.BasePresenter;
import com.hast.exchangerate.general.models.BANKS;
import com.hast.exchangerate.mvp.models.IUserData;
import com.hast.exchangerate.mvp.presenters.IChooseBankPresenter;
import com.hast.exchangerate.mvp.views.IChooseBankView;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

public class ChooseBankPresenter extends BasePresenter<IChooseBankView> implements IChooseBankPresenter<IChooseBankView> {

    @Inject
    IUserData userData;

    /**
     * @return list of all banks(without settings) that was chosen by user.
     */
    @Override
    public List<BANKS> getChosenBanks() {
        return userData.getRawBanksList();
    }

    /**
     * Add's banks that wasn't in the list before, banks that was on the list and still there,
     * keeps their settings, old banks that are not in the list anymore, gets deleted.
     * Also saves this data to the DB.
     *
     * @param banks list of banks to save
     */
    @Override
    public void saveChosenBanks(List<BANKS> banks) {
        userData.setChosenBanks(banks);
        userData.saveData();
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
