package com.hast.exchangerate.di.modules;

import com.hast.exchangerate.general.db.DatabaseManager;
import com.hast.exchangerate.general.models.UserData;
import com.hast.exchangerate.mvp.models.ABankApiImpl;
import com.hast.exchangerate.mvp.models.AlfabankApiImpl;
import com.hast.exchangerate.mvp.models.IBaseApi;
import com.hast.exchangerate.mvp.models.IDatabaseManager;
import com.hast.exchangerate.mvp.models.IUnifiedModel;
import com.hast.exchangerate.mvp.models.IUserData;
import com.hast.exchangerate.mvp.models.NBUApi;
import com.hast.exchangerate.mvp.models.NBUApiImpl;
import com.hast.exchangerate.mvp.models.OtpBankApiImpl;
import com.hast.exchangerate.mvp.models.PrivateBankApiImpl;
import com.hast.exchangerate.mvp.models.UnifiedModel;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Konstantyn Zakharchenko on 18.05.2017.
 */

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
    @Named("alfabank")
    IBaseApi provideAlfabankApi() {
        return new AlfabankApiImpl();
    }

    @Singleton
    @Provides
    @Named("otpbankApi")
    IBaseApi provideOTPbankApi() {
        return new OtpBankApiImpl();
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
