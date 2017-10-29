package com.znshadows.rateofexchange.mvp.views;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponse;

import java.util.List;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.widget_settings.WidgetSettingsActivity}
 */
public interface IWidgetSettingsView extends IBaseView {
    void showCurrenciesList(List<UnifiedBankResponse> dataList);
}
