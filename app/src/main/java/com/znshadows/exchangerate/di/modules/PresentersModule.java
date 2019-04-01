package com.znshadows.exchangerate.di.modules;


import com.znshadows.exchangerate.general.activities.choose_bank.ChooseBankPresenter;
import com.znshadows.exchangerate.general.activities.loading.LoadingPresenter;
import com.znshadows.exchangerate.general.activities.main.MainPresenter;
import com.znshadows.exchangerate.general.activities.rate_list.BankRatesPresenter;
import com.znshadows.exchangerate.general.activities.widget_settings.WidgetSettingsPresenter;
import com.znshadows.exchangerate.general.widget.WidgetPresenter;
import com.znshadows.exchangerate.mvp.presenters.IBankRatesPresenter;
import com.znshadows.exchangerate.mvp.presenters.IChooseBankPresenter;
import com.znshadows.exchangerate.mvp.presenters.ILoadingPresenter;
import com.znshadows.exchangerate.mvp.presenters.IMainPresenter;
import com.znshadows.exchangerate.mvp.presenters.IWidgetPresenter;
import com.znshadows.exchangerate.mvp.presenters.IWidgetSettingsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Natali Zakharchenko on 2/27/17.
 */
@Singleton
@Module
public class PresentersModule {

    @Singleton
    @Provides
    ILoadingPresenter provideLoadingPresenter() {
        return new LoadingPresenter();
    }

    @Singleton
    @Provides
    IMainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Singleton
    @Provides
    IChooseBankPresenter provideChooseBankPresenter() {
        return new ChooseBankPresenter();
    }

    @Singleton
    @Provides
    IBankRatesPresenter provideBankRatesPresenter() {
        return new BankRatesPresenter();
    }

    @Singleton
    @Provides
    IWidgetSettingsPresenter provideWidgetSettingsPresenter() {
        return new WidgetSettingsPresenter();
    }

    @Singleton
    @Provides
    IWidgetPresenter provideWidgetPresenter() {
        return new WidgetPresenter();
    }
}
