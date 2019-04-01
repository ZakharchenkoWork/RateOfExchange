package com.znshadows.exchangerate.general.db;

/**
 * Created by Natali Zakharchenko on 30.05.2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.znshadows.exchangerate.general.models.UserData;
import com.znshadows.exchangerate.general.models.WidgetInfo;


import java.sql.SQLException;

/**
 * Created by Natali Zakharchenko on 07.01.2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<UserData, String> userDao = null;
    private Dao<WidgetInfo, String> widgetsDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Call createTable statements here to create * the tables that will store data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource, UserData.class);
            TableUtils.createTable(connectionSource, WidgetInfo.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This is called when application is upgraded and it has a higher version number. This allows you to adjust * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, UserData.class, true);
            TableUtils.dropTable(connectionSource, WidgetInfo.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our UserData class. It will create it or just give the cached * value.
     */
    Dao<UserData, String> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(UserData.class);
        }
        return userDao;
    }

    /**
     * Returns the Database Access Object (DAO) for our WidgetInfo class. It will create it or just give the cached * value.
     */
    Dao<WidgetInfo, String> getWidgetsDao() throws SQLException {
        if (widgetsDao == null) {
            widgetsDao = getDao(WidgetInfo.class);
        }
        return widgetsDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        userDao = null;
        widgetsDao = null;
    }
}
