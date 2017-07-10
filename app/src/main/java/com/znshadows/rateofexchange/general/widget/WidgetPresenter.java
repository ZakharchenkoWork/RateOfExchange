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


    @Override
    public void getBankData(int widgetId) {
        WidgetInfo widgetInfo = databaseManager.getWidgetInfo(widgetId);
        if(widgetInfo == null){
            return;
        }
        model.getTodaysRates(widgetInfo.getChoosenBank(), widgetInfo.getChoosenCurrency()).subscribe(
                getObserver(true, (bankResponse) -> getView().showResponce(widgetInfo, bankResponse)));
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
