package com.znshadows.exchangerate.general.widget;

import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.general.activities.BasePresenter;
import com.znshadows.exchangerate.general.models.WidgetInfo;
import com.znshadows.exchangerate.mvp.models.IDatabaseManager;
import com.znshadows.exchangerate.mvp.models.IUnifiedModel;
import com.znshadows.exchangerate.mvp.presenters.IWidgetPresenter;
import com.znshadows.exchangerate.mvp.views.IWidgetView;

import javax.inject.Inject;


/**
 * Created by Natali Zakharchenko on 17.05.2017.
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
