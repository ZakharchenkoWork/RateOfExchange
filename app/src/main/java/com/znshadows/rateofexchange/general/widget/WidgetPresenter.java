package com.znshadows.rateofexchange.general.widget;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.WidgetInfo;
import com.znshadows.rateofexchange.mvp.models.IDatabaseManager;
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
    @Inject
    IDatabaseManager databaseManager;
    /**
     * Searching for the widgwets settings saved in the database,
     * <b> WARNING: </b> returs null if there is no settings for this widget id.
     * @param widgetId providd by appWidgetManager.getAppWidgetIds(ComponentName)
     * @return settings for this widget
     */
    @Override
    public WidgetInfo getWidgetSettings(int widgetId){
        return databaseManager.getWidgetInfo(widgetId);
    }
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
    public void getBankData(int widgetId) {
        WidgetInfo widgetInfo = databaseManager.getWidgetInfo(widgetId);
        model.getTodaysRates(widgetInfo.getChoosenBank(), widgetInfo.getChoosenCurrency()).subscribe(
                getObservable(true, (bankResponse) -> getView().showResponce(widgetInfo, bankResponse)));
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
