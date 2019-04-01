package com.znshadows.exchangerate.mvp.models;

import android.content.Context;

import com.znshadows.exchangerate.general.models.UserData;
import com.znshadows.exchangerate.general.models.WidgetInfo;

/**
 * Created by Natali Zakharchenko on 27.06.2017.
 */


/**
 * used in {@link com.znshadows.exchangerate.general.db.DatabaseManager}
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
