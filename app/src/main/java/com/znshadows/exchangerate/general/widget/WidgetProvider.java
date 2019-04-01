package com.znshadows.exchangerate.general.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.R;
import com.znshadows.exchangerate.general.models.UnifiedBankResponse;
import com.znshadows.exchangerate.general.models.WidgetInfo;
import com.znshadows.exchangerate.mvp.presenters.IWidgetPresenter;
import com.znshadows.exchangerate.mvp.views.IWidgetView;

import javax.inject.Inject;

/**
 * Created by Natali Zakharchenko on 24.05.2017.
 */

public class WidgetProvider extends AppWidgetProvider implements IWidgetView {
    @Inject
    IWidgetPresenter presenter;

    AppWidgetManager appWidgetManager;
    Context context;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        this.context = context;
        // Retrieve the widget’s layout//
        this.appWidgetManager = appWidgetManager;
        App.getAppComponent().inject(this);
        presenter.setView(this);
        //views = new RemoteViews(context.getPackageName(), R.layout.activity_main);

        ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);

        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {
            // create some random data
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            Log.w("WidgetExample", "works");
            // Set the text


            // Register an onClickListener
            Intent intent = new Intent(context, WidgetProvider.class);

            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.update, pendingIntent);
            presenter.getBankData(widgetId);

        }


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
    public void showResponse(WidgetInfo widgetInfo, UnifiedBankResponse bankResponse) {
        Log.d("showResponse", "done");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        views.setTextViewText(R.id.bankName, widgetInfo.getChoosenBank().name());
        views.setTextViewText(R.id.rateToBuy, "" + bankResponse.getBuy());
        views.setTextViewText(R.id.rateToSale, "" + bankResponse.getSale());
        appWidgetManager.updateAppWidget(widgetInfo.getWidgetId(), views);
    }
}
