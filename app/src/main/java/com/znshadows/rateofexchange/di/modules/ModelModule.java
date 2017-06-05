package com.znshadows.rateofexchange.di.modules;

import com.znshadows.rateofexchange.mvp.models.ABankApiImpl;
import com.znshadows.rateofexchange.mvp.models.IBaseApi;
import com.znshadows.rateofexchange.mvp.models.IUnifiedModel;
import com.znshadows.rateofexchange.mvp.models.NBUApi;
import com.znshadows.rateofexchange.mvp.models.NBUApiImpl;
import com.znshadows.rateofexchange.mvp.models.PrivateBankApiImpl;
import com.znshadows.rateofexchange.mvp.models.UnifiedModel;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kostya on 18.05.2017.
 */
@Singleton
@Module
public class ModelModule {

    @Singleton @Provides
    NBUApi provideApiNBU(){
        return new NBUApiImpl();
    }

    @Singleton @Provides @Named("nbu")
    IBaseApi provideNBUApi(){
        return new NBUApiImpl();
    }

    @Singleton @Provides  @Named("pb")
    IBaseApi providePrivateBankApi(){
        return new PrivateBankApiImpl();
    }
    @Singleton @Provides  @Named("abank")
    IBaseApi provideABankApi(){
        return new ABankApiImpl();
    }

    @Singleton @Provides
    IUnifiedModel provideUnifiedModel(){
        return new UnifiedModel();
    }
}
