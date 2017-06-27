package com.znshadows.rateofexchange.mvp.models;

import android.content.Context;

import com.znshadows.rateofexchange.general.models.WidgetInfo;

/**
 * Created by kostya on 27.06.2017.
 */

public interface IDatabaseManager {
    void setHelper(Context context);

    void releaseHelper();

    void saveWidgetInfo(WidgetInfo widgetInfo);

    WidgetInfo getWidgetInfo(int widgetId);
}
