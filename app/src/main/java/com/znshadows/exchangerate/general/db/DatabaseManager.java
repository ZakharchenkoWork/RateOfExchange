package com.znshadows.exchangerate.general.db;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.znshadows.exchangerate.general.models.UserData;
import com.znshadows.exchangerate.general.models.WidgetInfo;
import com.znshadows.exchangerate.mvp.models.IDatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Natali Zakharchenko on 26.06.2017.
 */

public class DatabaseManager implements IDatabaseManager {

    private DatabaseHelper databaseHelper;



    @Override
    public void setup(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

    @Override
    public void release() {
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }

    @Override
    public void saveWidgetInfo(WidgetInfo widgetInfo) {
        try {
            databaseHelper.getWidgetsDao().createOrUpdate(widgetInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searching for the widgwets settings saved in the database,
     * <b> WARNING: </b> returs null if there is no settings for this widget id.
     *
     * @param widgetId providd by appWidgetManager.getAppWidgetIds(ComponentName)
     * @return settings for this widget
     */
    @Override
    public WidgetInfo getWidgetInfo(int widgetId) {
        try {
            return databaseHelper.getWidgetsDao().queryForId("" + widgetId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public @NonNull UserData getLastUserData(){
        try {
            List<UserData> oldUsers = databaseHelper.getUserDao().queryForAll();
            if(oldUsers != null && oldUsers.size() > 0) {
                return oldUsers.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new UserData();
    }

    @Override
    public void saveUserData(UserData userData) {
        try {
            Dao.CreateOrUpdateStatus status = databaseHelper.getUserDao().createOrUpdate(userData);
            Log.v("saveUserData", "status.isCreated() "+status.isCreated());
            Log.v("saveUserData", "+status.isUpdated() "+status.isUpdated());
            Log.v("saveUserData", "+status.getNumLinesChanged() "+status.getNumLinesChanged());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
