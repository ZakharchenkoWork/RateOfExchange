package com.znshadows.exchangerate.di.modules;

import com.znshadows.exchangerate.general.db.DatabaseManager;
import com.znshadows.exchangerate.general.models.UserData;
import com.znshadows.exchangerate.mvp.models.ABankApiImpl;
import com.znshadows.exchangerate.mvp.models.IBaseApi;
import com.znshadows.exchangerate.mvp.models.IDatabaseManager;
import com.znshadows.exchangerate.mvp.models.IUnifiedModel;
import com.znshadows.exchangerate.mvp.models.IUserData;
import com.znshadows.exchangerate.mvp.models.NBUApi;
import com.znshadows.exchangerate.mvp.models.NBUApiImpl;
import com.znshadows.exchangerate.mvp.models.PrivateBankApiImpl;
import com.znshadows.exchangerate.mvp.models.UnifiedModel;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Natali Zakharchenko on 18.05.2017.
 */
@Singleton
@Module
public class ModelModule {
    @Singleton
    @Provides
    IUserData provideUserData() {
        return new UserData();
    }

    @Singleton
    @Provides
    NBUApi provideApiNBU() {
        return new NBUApiImpl();
    }

    @Singleton
    @Provides
    @Named("nbu")
    IBaseApi provideNBUApi() {
        return new NBUApiImpl();
    }

    @Singleton
    @Provides
    @Named("pb")
    IBaseApi providePrivateBankApi() {
        return new PrivateBankApiImpl();
    }

    @Singleton
    @Provides
    @Named("abank")
    IBaseApi provideABankApi() {
        return new ABankApiImpl();
    }

    @Singleton
    @Provides
    IUnifiedModel provideUnifiedModel() {
        return new UnifiedModel();
    }

    @Singleton
    @Provides
    IDatabaseManager provideDatabaseManager() {
        return new DatabaseManager();
    }
}
