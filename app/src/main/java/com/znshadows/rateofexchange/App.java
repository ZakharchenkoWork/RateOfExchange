package com.znshadows.rateofexchange;

import android.app.Application;

import com.znshadows.rateofexchange.di.components.AppComponent;
import com.znshadows.rateofexchange.di.components.DaggerAppComponent;

/**
 * Created by kostya on 18.05.2017.
 */

public class App extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }


    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
