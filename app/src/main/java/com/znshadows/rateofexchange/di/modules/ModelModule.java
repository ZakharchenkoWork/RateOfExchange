package com.znshadows.rateofexchange.di.modules;

import com.znshadows.rateofexchange.mvp.models.NBUApi;
import com.znshadows.rateofexchange.mvp.models.NBUApiImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kostya on 18.05.2017.
 */
@Singleton
@Module
public class ModelModule {
    @Singleton
    @Provides
    NBUApi provideApiNBU(){
        return new NBUApiImpl();
    }
  /*  @Singleton
    @Provides
    DriversedApi provideApi(){
        return new ModelImpl();
    }*/
}
