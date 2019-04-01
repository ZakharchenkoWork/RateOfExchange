package com.znshadows.exchangerate.mvp.views;

import com.znshadows.exchangerate.general.models.UnifiedBankResponse;

import java.util.List;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.exchangerate.general.activities.widget_settings.WidgetSettingsActivity}
 */
public interface IWidgetSettingsView extends IBaseView {
    void showCurrenciesList(List<UnifiedBankResponse> dataList);
}
