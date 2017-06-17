package com.znshadows.rateofexchange.general.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.activities.loading.LoadingActivity;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;
import com.znshadows.rateofexchange.mvp.presenters.IWidgetPresenter;
import com.znshadows.rateofexchange.mvp.views.IWidgetView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by kostya on 24.05.2017.
 */

public class WidgetProvider extends AppWidgetProvider implements IWidgetView{
    @Inject
    IWidgetPresenter presenter;
    RemoteViews views;
    AppWidgetManager appWidgetManager;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        // Retrieve the widget’s layout//
        this.appWidgetManager = appWidgetManager;
        App.getAppComponent().inject(this);
        presenter.setView(this);
        //views = new RemoteViews(context.getPackageName(), R.layout.activity_main);

        ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);

        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {
            // create some random data


            views = new RemoteViews(context.getPackageName(),
                    R.layout.widget_layout);
            Log.w("WidgetExample", "works");
            // Set the text


            // Register an onClickListener
            Intent intent = new Intent(context, WidgetProvider.class);

            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.update, pendingIntent);


        }

        presenter.getRatesInMyBank(allWidgetIds);
// Tell the AppWidgetManager to perform an update on this application widget//
//        appWidgetManager.updateAppWidget(currentWidgetId, views);
    }


    @Override
    public void onError() {

    }

    @Override
    public void onStartLoading() {

    }

    @Override
    public void onFinishLoading() {

    }

    @Override
    public void showResponce(int[] allWidgetIds, List<UnifiedBankResponce> bankResponse) {
        for (int widgetId : allWidgetIds) {
        Log.d("showResponce", "done");
        Log.d("showResponce", "" +bankResponse.get(0).getBuy());
        views.setTextViewText(R.id.rateToBuy, ""+bankResponse.get(0).getBuy());
            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }
}
