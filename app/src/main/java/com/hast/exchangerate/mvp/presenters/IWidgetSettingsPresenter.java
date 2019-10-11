package com.hast.exchangerate.mvp.presenters;


import com.hast.exchangerate.general.models.BANKS;
import com.hast.exchangerate.general.models.WidgetInfo;
import com.hast.exchangerate.mvp.views.IWidgetSettingsView;

import java.util.List;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.hast.exchangerate.general.activities.widget_settings.WidgetSettingsPresenter}
 *
 * @param <ViewType> Activity
 */
public interface IWidgetSettingsPresenter<ViewType extends IWidgetSettingsView> extends IBasePresenter<ViewType> {

    /**
     * Gets list of currencies for the specified bank.
     * <p>
     * After finish calls {@link IWidgetSettingsView#showCurrenciesList(List)}
     *
     * @param chosenBank
     */
    void getCurrenciesForBank(BANKS chosenBank);

    /**
     * Saves setting for this widget to the DB
     *
     * @param widgetInfo to be saved.
     */
    void saveWidgetInfo(WidgetInfo widgetInfo);
}
