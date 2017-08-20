package com.znshadows.rateofexchange.mvp.presenters;


import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.WidgetInfo;
import com.znshadows.rateofexchange.mvp.views.IWidgetSettingsView;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.widget_settings.WidgetSettingsPresenter}
 * @param <ViewType> Activity
 */
public interface IWidgetSettingsPresenter<ViewType extends IWidgetSettingsView> extends IBasePreseter<ViewType> {

    /**
     *
     * Gets list of currencies for the specified bank.
     * <p>
     * After finish calls {@link IWidgetSettingsView#showCurrenciesList(List)}
     * @param choosenBank
     */
    void getCurrenciesForBank(BANKS choosenBank);

    /**
     * Saves setting for this widget to the DB
     * @param widgetInfo to be saved.
     */
    void saveWidgetInfo(WidgetInfo widgetInfo);
}
