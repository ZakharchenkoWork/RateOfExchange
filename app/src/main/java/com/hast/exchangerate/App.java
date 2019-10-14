package com.hast.exchangerate;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;
import com.hast.exchangerate.di.components.AppComponent;

import com.hast.exchangerate.di.components.DaggerAppComponent;
import com.hast.exchangerate.mvp.models.IDatabaseManager;

import javax.inject.Inject;

/**
 * Created by Konstantyn Zakharchenko on 18.05.2017.
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
        MobileAds.initialize(this, initializationStatus-> Log.i("AdMob","OnInitializationCompleteListener"));
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
