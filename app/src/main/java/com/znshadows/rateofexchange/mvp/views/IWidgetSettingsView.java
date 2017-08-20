package com.znshadows.rateofexchange.mvp.views;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.widget_settings.WidgetSettingsActivity}
 */
public interface IWidgetSettingsView extends IBaseView{
    void showCurrenciesList(List<UnifiedBankResponce> dataList);
}
