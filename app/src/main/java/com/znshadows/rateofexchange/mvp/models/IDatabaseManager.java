package com.znshadows.rateofexchange.mvp.models;

import android.content.Context;

import com.znshadows.rateofexchange.general.models.UserData;
import com.znshadows.rateofexchange.general.models.WidgetInfo;

/**
 * Created by kostya on 27.06.2017.
 */


/**
 * used in {@link com.znshadows.rateofexchange.general.db.DatabaseManager}
 */
public interface IDatabaseManager {

    /**
     * Call when app lunched first time
     * @param context app context
     */
    void setup(Context context);

    /**
     * Call when app is closed
     */
    void release();

    void saveWidgetInfo(WidgetInfo widgetInfo);

    WidgetInfo getWidgetInfo(int widgetId);

    void saveUserData(UserData userData);

    UserData getLastUserData();



}
