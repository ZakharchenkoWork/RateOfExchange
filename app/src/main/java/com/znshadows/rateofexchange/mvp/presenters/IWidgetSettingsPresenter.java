package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.WidgetInfo;
import com.znshadows.rateofexchange.mvp.views.IBaseView;
import com.znshadows.rateofexchange.mvp.views.IWidgetSettingsView;

/**
 * Created by kostya on 17.05.2017.
 */

public interface IWidgetSettingsPresenter<ViewType extends IWidgetSettingsView> extends IBasePreseter<ViewType> {
    void getCurrenciesForBank(BANKS choosenBank);

    void saveWidgetInfo(WidgetInfo widgetInfo);
}
