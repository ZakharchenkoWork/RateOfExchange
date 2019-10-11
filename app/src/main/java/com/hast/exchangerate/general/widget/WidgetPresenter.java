package com.hast.exchangerate.general.widget;

import com.hast.exchangerate.App;
import com.hast.exchangerate.general.activities.BasePresenter;
import com.hast.exchangerate.general.models.WidgetInfo;
import com.hast.exchangerate.mvp.models.IDatabaseManager;
import com.hast.exchangerate.mvp.models.IUnifiedModel;
import com.hast.exchangerate.mvp.presenters.IWidgetPresenter;
import com.hast.exchangerate.mvp.views.IWidgetView;

import javax.inject.Inject;


/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

public class WidgetPresenter extends BasePresenter<IWidgetView> implements IWidgetPresenter<IWidgetView> {

    @Inject
    IUnifiedModel model;
    @Inject
    IDatabaseManager databaseManager;


    @Override
    public void getBankData(int widgetId) {
        WidgetInfo widgetInfo = databaseManager.getWidgetInfo(widgetId);
        if (widgetInfo == null) {
            return;
        }
        model.getTodayRates(widgetInfo.getChoosenBank(), widgetInfo.getChoosenCurrency()).subscribe(
                getObserver(true, (bankResponse) -> getView().showResponse(widgetInfo, bankResponse)));
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
