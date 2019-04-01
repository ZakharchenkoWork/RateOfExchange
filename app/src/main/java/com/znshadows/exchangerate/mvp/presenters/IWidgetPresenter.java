package com.znshadows.exchangerate.mvp.presenters;

import com.znshadows.exchangerate.mvp.views.IWidgetView;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
 */

public interface IWidgetPresenter<ViewType extends IWidgetView> extends IBasePresenter<ViewType> {

    void getBankData(int widgetId);
}
