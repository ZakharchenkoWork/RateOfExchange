package com.znshadows.exchangerate.mvp.presenters;


import com.znshadows.exchangerate.general.models.BANKS;
import com.znshadows.exchangerate.general.models.WidgetInfo;
import com.znshadows.exchangerate.mvp.views.IWidgetSettingsView;

import java.util.List;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.exchangerate.general.activities.widget_settings.WidgetSettingsPresenter}
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
