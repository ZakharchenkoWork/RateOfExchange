package com.znshadows.rateofexchange.general.widget;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;
import com.znshadows.rateofexchange.mvp.presenters.IWidgetPresenter;
import com.znshadows.rateofexchange.mvp.views.IWidgetView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by kostya on 17.05.2017.
 */

public class WidgetPresenter extends BasePresenter<IWidgetView> implements IWidgetPresenter<IWidgetView> {

    @Inject
    IUnifiedModel model;

    @Override
    public List<BANKS> getChoosenBanks() {
        List<BANKS> banks = new ArrayList<>();
        banks.add(BANKS.NBU);
        return banks;
    }
    @Override
    public void getRatesInMyBank(int[] allWidgetIds) {
        model.getTodaysList(getChoosenBanks().get(0)).subscribe(
                getObservable(true, (bankResponse) -> getView().showResponce(allWidgetIds, bankResponse)));
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
