package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.mvp.views.IWidgetView;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

public interface IWidgetPresenter<ViewType extends IWidgetView> extends IBasePresenter<ViewType> {

    void getBankData(int widgetId);
}
