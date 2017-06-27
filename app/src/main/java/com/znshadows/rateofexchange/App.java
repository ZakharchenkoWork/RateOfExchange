package com.znshadows.rateofexchange;

import android.app.Application;

import com.znshadows.rateofexchange.di.components.AppComponent;
import com.znshadows.rateofexchange.di.components.DaggerAppComponent;
import com.znshadows.rateofexchange.mvp.models.IDatabaseManager;

import javax.inject.Inject;

/**
 * Created by kostya on 18.05.2017.
 */

public class App extends Application {
    private static AppComponent appComponent;
    @Inject
    IDatabaseManager databaseManager;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
        appComponent.inject(this);
    databaseManager.setHelper(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        databaseManager.releaseHelper();
        super.onTerminate();
    }
    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
