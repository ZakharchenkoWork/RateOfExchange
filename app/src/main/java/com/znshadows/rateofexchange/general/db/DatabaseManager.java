package com.znshadows.rateofexchange.general.db;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.znshadows.rateofexchange.general.models.WidgetInfo;
import com.znshadows.rateofexchange.mvp.models.IDatabaseManager;

import java.sql.SQLException;

/**
 * Created by kostya on 26.06.2017.
 */

public class DatabaseManager implements IDatabaseManager {

        private DatabaseHelper databaseHelper;
@Override
        public void setHelper(Context context){
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        @Override
        public void releaseHelper(){
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
// Dao<Book, String> bookDao;
// try {
// bookDao = DatabaseManager.getInstance().getHelper().getBookDao();
// Book book = new book();
// book.setId(id);
// book.setTitle(some_title);
// bookDao.create(book); //создаем книгу
// Book b = bookDao.queryForId(id); //получаем книгу по id
// bookDao.delete(b); //удаляем книгу
// } catch (SQLException e) { e.printStackTrace(); }
    }
