package com.znshadows.rateofexchange.general.activities.main;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.ChoosenBank;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;

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


    @Override
    public void getChoosenBanks() {
        List<ChoosenBank> banks = new ArrayList<>();
        banks.add(new ChoosenBank(BANKS.NBU));
        getView().showChoosenBanks(banks);
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
