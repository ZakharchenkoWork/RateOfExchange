package com.znshadows.rateofexchange.general;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.znshadows.rateofexchange.R;

/**
 * Created by kostya on 24.05.2017.
 */

public class WidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        // Retrieve the widget’s layout//
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);

// Tell the AppWidgetManager to perform an update on this application widget//
//        appWidgetManager.updateAppWidget(currentWidgetId, views);
    }


}
