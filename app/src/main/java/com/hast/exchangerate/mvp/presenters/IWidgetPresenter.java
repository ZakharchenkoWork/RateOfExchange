package com.hast.exchangerate.mvp.presenters;

import com.hast.exchangerate.mvp.views.IWidgetView;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

public interface IWidgetPresenter<ViewType extends IWidgetView> extends IBasePresenter<ViewType> {

    void getBankData(int widgetId);
}
