package com.znshadows.rateofexchange.general.activities.main;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;

import com.znshadows.rateofexchange.mvp.models.UnifiedModel;
import com.znshadows.rateofexchange.mvp.presenters.IMainPresenter;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import javax.inject.Inject;


/**
 * Created by kostya on 17.05.2017.
 */

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter<IMainView> {
    @Inject
    IUnifiedModel model;



    public void getNBU(){
        model.getTodaysList(UnifiedModel.BANKS.NBU).subscribe(
                getObservable(true, (bankResponse)->getView().showResponce(bankResponse)));
        }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
