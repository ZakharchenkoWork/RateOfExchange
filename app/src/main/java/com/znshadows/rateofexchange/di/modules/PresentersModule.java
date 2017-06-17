package com.znshadows.rateofexchange.di.modules;

import com.znshadows.rateofexchange.general.activities.choose_bank.ChoosseBankPresenter;
import com.znshadows.rateofexchange.general.activities.loading.LoadingPresenter;
import com.znshadows.rateofexchange.general.activities.main.MainPresenter;
import com.znshadows.rateofexchange.general.activities.rate_list.BankRatesPresenter;
import com.znshadows.rateofexchange.general.widget.WidgetPresenter;
import com.znshadows.rateofexchange.mvp.presenters.IBankRatesPresenter;
import com.znshadows.rateofexchange.mvp.presenters.IChoosseBankPresenter;
import com.znshadows.rateofexchange.mvp.presenters.ILoadingPresenter;
import com.znshadows.rateofexchange.mvp.presenters.IMainPresenter;
import com.znshadows.rateofexchange.mvp.presenters.IWidgetPresenter;

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
    @Singleton
    @Provides
    IChoosseBankPresenter provideChooseBankPresenter(){
        return new ChoosseBankPresenter();
    }

    @Singleton
    @Provides
    IBankRatesPresenter provideBankRatesPresenter(){
        return new BankRatesPresenter();
    }
    @Singleton
    @Provides
    IWidgetPresenter provideWidgetPresenter(){
        return new WidgetPresenter();
    }
}
