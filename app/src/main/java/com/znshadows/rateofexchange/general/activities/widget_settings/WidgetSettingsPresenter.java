package com.znshadows.rateofexchange.general.activities.widget_settings;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponse;
import com.znshadows.rateofexchange.general.models.WidgetInfo;
import com.znshadows.rateofexchange.mvp.models.IDatabaseManager;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;
import com.znshadows.rateofexchange.mvp.presenters.IWidgetSettingsPresenter;
import com.znshadows.rateofexchange.mvp.views.IWidgetSettingsView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Konstantyn Zakharchenko on 22.06.2017.
 */

public class WidgetSettingsPresenter extends BasePresenter<IWidgetSettingsView> implements IWidgetSettingsPresenter<IWidgetSettingsView> {
    @Inject
    IUnifiedModel model;
    @Inject
    IDatabaseManager databaseManager;

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }

    /**
     * Gets list of currencies for the specified bank.
     * After finish calls {@link IWidgetSettingsView#showCurrenciesList(List<UnifiedBankResponse>)}
     *
     * @param chosenBank requested bank
     */
    @Override
    public void getCurrenciesForBank(BANKS chosenBank) {
        model.getTodayList(chosenBank).subscribe(
                getObserver(true, (dataList) -> {
                    getView().showCurrenciesList(dataList);
                }));
    }

    @Override
    public void saveWidgetInfo(WidgetInfo widgetInfo) {
        databaseManager.saveWidgetInfo(widgetInfo);
    }
}
