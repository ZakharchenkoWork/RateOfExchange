package com.znshadows.rateofexchange.di.components;

import com.znshadows.rateofexchange.di.modules.ModelModule;
import com.znshadows.rateofexchange.di.modules.PresentersModule;
import com.znshadows.rateofexchange.general.activities.loading.LoadingActivity;
import com.znshadows.rateofexchange.general.activities.main.MainActivity;
import com.znshadows.rateofexchange.general.activities.main.MainPresenter;
import com.znshadows.rateofexchange.mvp.models.UnifiedModel;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Evolution on 2/27/17.
 */

@Singleton
@Component(modules = {ModelModule.class, PresentersModule.class})
public interface AppComponent {

    void inject(LoadingActivity loadingActivity);
    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);

    void inject(UnifiedModel unifiedModel);
}
