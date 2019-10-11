package com.hast.exchangerate.mvp.models;

import android.content.Context;

import com.hast.exchangerate.general.models.UserData;
import com.hast.exchangerate.general.models.WidgetInfo;

/**
 * Created by Konstantyn Zakharchenko on 27.06.2017.
 */


/**
 * used in {@link com.hast.exchangerate.general.db.DatabaseManager}
 */
public interface IDatabaseManager {

    /**
     * Call when app lunched first time
     *
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
