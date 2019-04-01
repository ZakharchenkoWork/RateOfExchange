package com.znshadows.exchangerate;

import android.app.Application;

import com.znshadows.exchangerate.di.components.AppComponent;
import com.znshadows.exchangerate.di.components.DaggerAppComponent;
import com.znshadows.exchangerate.mvp.models.IDatabaseManager;

import javax.inject.Inject;

/**
 * Created by Natali Zakharchenko on 18.05.2017.
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
        databaseManager.setup(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        databaseManager.release();
        super.onTerminate();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
