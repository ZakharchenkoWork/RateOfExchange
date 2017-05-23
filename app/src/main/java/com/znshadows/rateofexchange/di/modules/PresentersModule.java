package com.znshadows.rateofexchange.di.modules;

import com.znshadows.rateofexchange.general.activities.loading.LoadingPresenter;
import com.znshadows.rateofexchange.general.activities.main.MainPresenter;
import com.znshadows.rateofexchange.mvp.presenters.ILoadingPresenter;
import com.znshadows.rateofexchange.mvp.presenters.IMainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evolution on 2/27/17.
 */
@Singleton
@Module
public class PresentersModule {

    @Singleton
    @Provides
    ILoadingPresenter provideLoadingPresenter(){
        return new LoadingPresenter();
    }

    @Singleton
    @Provides
    IMainPresenter provideMainPresenter(){
        return new MainPresenter();
    }

}
