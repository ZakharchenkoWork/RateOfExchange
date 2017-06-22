package com.znshadows.rateofexchange.general.activities.choose_bank;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;
import com.znshadows.rateofexchange.mvp.models.IUserData;
import com.znshadows.rateofexchange.mvp.presenters.IChoosseBankPresenter;
import com.znshadows.rateofexchange.mvp.views.IChooseBankView;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by kostya on 17.05.2017.
 */

public class ChoosseBankPresenter extends BasePresenter<IChooseBankView> implements IChoosseBankPresenter<IChooseBankView> {

    @Inject
    IUserData userData;
    @Override
    public List<BANKS> getChoosenBanks() {

        return userData.getRawBanksList();
    }

    @Override
    public void saveChoosenBanks(List<BANKS> banks) {
        userData.saveChoosenBanks(banks);
    }

    @Override
    public void resolveDaggerDependencies() {
App.getAppComponent().inject(this);
    }
}
