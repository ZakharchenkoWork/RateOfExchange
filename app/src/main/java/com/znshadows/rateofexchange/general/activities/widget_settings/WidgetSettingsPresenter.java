package com.znshadows.rateofexchange.general.activities.widget_settings;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;
import com.znshadows.rateofexchange.mvp.presenters.IWidgetSettingsPresenter;
import com.znshadows.rateofexchange.mvp.views.IWidgetSettingsView;

import javax.inject.Inject;

/**
 * Created by kostya on 22.06.2017.
 */

public class WidgetSettingsPresenter extends BasePresenter<IWidgetSettingsView> implements IWidgetSettingsPresenter<IWidgetSettingsView> {
    @Inject
    IUnifiedModel model;
    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void getCurrenciesForBank(BANKS choosenBank) {
        model.getTodaysList(choosenBank).subscribe(
                getObservable(true, (dataList)->{
                    getView().showCurrenciesList(dataList);
                }));
    }
}
